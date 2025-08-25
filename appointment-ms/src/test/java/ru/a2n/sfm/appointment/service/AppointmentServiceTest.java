package ru.a2n.sfm.appointment.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class AppointmentServiceTest {

    @Autowired
    private AppointmentService service;

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

    @Test
    void shouldGetPersonIdByFacilityIdAndResponsibility() {
        var actual =
                service.getPersonIdByFacilityIdAndResponsibility("0198bd32-4a23-72c5-89a3-8bb0896beba2", "plumber");
        assertThat(actual)
                .isNotNull()
                .hasSize(2)
                .contains("0198bd32-4a23-7f31-822e-24da142d8081", "0198bd32-4a23-77b8-830f-13b45b62c682");
    }
}
