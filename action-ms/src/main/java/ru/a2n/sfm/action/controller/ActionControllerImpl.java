package ru.a2n.sfm.action.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.action.service.ActionService;
import ru.a2n.sfm.message.dto.MessageDto;

@SuppressWarnings("squid:S4449")
@RestController
public class ActionControllerImpl implements ActionController {

    private static final Logger logger = LoggerFactory.getLogger(ActionControllerImpl.class);

    private final ActionService actionService;

    public ActionControllerImpl(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    public ResponseEntity<Void> addMessage(MessageDto messageDto) {
        logger.info("addMessage {}", messageDto);
        actionService.addMessage(messageDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
