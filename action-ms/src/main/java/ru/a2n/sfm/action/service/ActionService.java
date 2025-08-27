package ru.a2n.sfm.action.service;

import static ru.a2n.sfm.message.dto.MessageTypes.isAlarm;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.a2n.sfm.action.dto.NotificationDto;
import ru.a2n.sfm.action.mapper.EventMapper;
import ru.a2n.sfm.action.model.Action;
import ru.a2n.sfm.action.model.Event;
import ru.a2n.sfm.action.model.Rule;
import ru.a2n.sfm.action.repository.EventRepository;
import ru.a2n.sfm.action.repository.RuleRepository;
import ru.a2n.sfm.appointment.client.AppointmentClient;
import ru.a2n.sfm.message.dto.MessageDto;
import ru.a2n.sfm.message.model.Status;

@SuppressWarnings("squid:S4449")
@Service
public class ActionService {

    private static final Logger logger = LoggerFactory.getLogger(ActionService.class);

    private final EventRepository repository;
    private final EventMapper mapper;
    private final RuleRepository ruleRepository;
    private final AppointmentClient appointmentClient;
    private final Counter alarmCounter;

    public ActionService(
            EventRepository repository,
            EventMapper mapper,
            RuleRepository ruleRepository,
            AppointmentClient appointmentClient,
            MeterRegistry meterRegistry) {
        this.repository = repository;
        this.mapper = mapper;
        this.ruleRepository = ruleRepository;
        this.appointmentClient = appointmentClient;
        this.alarmCounter = Counter.builder("sfm_alarm_messages_total")
                .description("Total number of alarm messages processed")
                .register(meterRegistry);
    }

    @Transactional
    public Event addMessage(MessageDto messageDto) {
        try {
            logger.info("add message with id {}", messageDto.messageId());
            Event personToSave = mapper.toEvent(messageDto);
            personToSave.setStatus(Status.SAVED);
            return repository.save(personToSave);
        } catch (Exception e) {
            logger.error("add exception {}", e.getMessage());
            return null;
        }
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processEvents() {
        logger.info("process events");
        List<Event> events = repository.getByStatus(Status.SAVED);
        events.forEach(this::processEvent);
    }

    public void processEvent(Event event) {
        logger.info("process event with id {}", event.getId());
        if (isAlarm(event.getMessageType())) {
            logger.info("ALARM DETECTED faciliti_id {} ", event.getFacilityId());
            alarmCounter.increment();
        }
        List<Rule> ruleList = getRuleForEvent(event);
        logger.info("found rule count {}", ruleList.size());
        if (ruleList.isEmpty() && !isAlarm(event.getMessageType())) {
            completeProcessing(event);
        } else {
            ruleList.forEach(rule -> {
                logger.info("apply rule with id {}", rule.getId());
                rule.getActionList().forEach(action -> applyAction(event, action));
                completeProcessing(event);
            });
        }
    }

    private void completeProcessing(Event event) {
        event.setStatus(Status.PROCESSED);
        event.setSentAt(LocalDateTime.now());
        repository.save(event);
    }

    private void applyAction(Event event, Action action) {
        String facilityId = event.getFacilityId();
        String responsibilityName = action.getResponsibilityName();
        List<String> personIdList =
                appointmentClient.getPersonIdListByFacilityIdAndResponsibility(facilityId, responsibilityName);
        logger.info(
                "for facility_id {} responcibility_name {} and personIdList {} ",
                facilityId,
                responsibilityName,
                personIdList);

        if (personIdList.isEmpty()) {
            logger.info("executor for event {} not found ", event);
            return;
        }
        String personId = personIdList.getFirst();
        NotificationDto notificationDto =
                new NotificationDto(facilityId, responsibilityName, personId, action.getActionName());
        logger.info("event {} notify  {} ", event, notificationDto);
    }

    private List<Rule> getRuleForEvent(Event event) {
        String messageType = event.getMessageType();
        return ruleRepository.getByMessageType(messageType);
    }
}
