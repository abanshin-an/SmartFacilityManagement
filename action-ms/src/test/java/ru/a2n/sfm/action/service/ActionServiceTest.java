package ru.a2n.sfm.action.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.a2n.sfm.action.model.Event;
import ru.a2n.sfm.appointment.client.AppointmentClient;
import ru.a2n.sfm.message.dto.MessageDto;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class ActionServiceTest {

    @Autowired
    private ActionService actionService;

    @MockitoBean
    AppointmentClient appointmentClient;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("sfm")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @BeforeEach
    void beforeEach() {
        buildAppointmentClient();
    }

    @Test
    void shouldAddMessage() {
        MessageDto messageDto = getALARMLeak();
        Event event = actionService.addMessage(messageDto);
        assertThat(event).isNotNull();
        assertThat(event.getId()).isNotNull();
    }

    private MessageDto getALARMLeak() {
        return new MessageDto(
                "0198c2e2-1059-7204-9a36-55fa5dbea22b",
                "0198bd32-4a23-72c5-89a3-8bb0896beba2",
                "ALARMLeak",
                0,
                null,
                LocalDateTime.now(),
                null,
                null);
    }

    private void buildAppointmentClient() {
        doReturn(List.of("0198bd32-4a23-7f31-822e-24da142d8081", "0198bd32-4a23-77b8-830f-13b45b62c682"))
                .when(appointmentClient)
                .getPersonIdListByFacilityIdAndResponsibility(any(), any());
    }
}
