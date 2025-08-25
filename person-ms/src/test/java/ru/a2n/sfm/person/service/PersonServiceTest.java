package ru.a2n.sfm.person.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
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
import ru.a2n.sfm.person.dto.PersonCreateDto;
import ru.a2n.sfm.person.dto.PersonFullDto;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class PersonServiceTest {
    @Autowired
    private PersonService personService;

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
    void shouldCreateAndRetrievePerson() {
        PersonCreateDto request = new PersonCreateDto(
                "Александр Семенович Иванов", "testPerson", "test@example.com", "+1234567890", "P@$$w0rd");

        Optional<PersonFullDto> optionalResponse = personService.createPerson(request);
        assertThat(optionalResponse).isPresent();
        PersonFullDto person = optionalResponse.get();
        assertThat(person.id()).isNotNull();
        assertThat(person.fullName()).isEqualTo("Александр Семенович Иванов");
        assertThat(person.login()).isEqualTo("testPerson");
        assertThat(person.email()).isEqualTo("test@example.com");
        assertThat(person.phone()).isEqualTo("+1234567890");

        Optional<PersonFullDto> optionalRetrieved = personService.getPersonById(person.id());
        assertThat(optionalRetrieved).isPresent();
        PersonFullDto personRetrieved = optionalRetrieved.get();

        assertThat(personRetrieved.id()).isEqualTo(person.id());
        assertThat(person.fullName()).isEqualTo("Александр Семенович Иванов");
        assertThat(personRetrieved.login()).isEqualTo("testPerson");
        assertThat(personRetrieved.email()).isEqualTo("test@example.com");
        assertThat(personRetrieved.phone()).isEqualTo("+1234567890");
    }
}
