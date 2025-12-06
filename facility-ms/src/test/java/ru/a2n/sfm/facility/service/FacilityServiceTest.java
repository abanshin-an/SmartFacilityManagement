package ru.a2n.sfm.facility.service;

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
import ru.a2n.sfm.facility.dto.FacilityCreateDto;
import ru.a2n.sfm.facility.dto.FacilityFullDto;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class FacilityServiceTest {
    @Autowired
    private FacilityService facilityService;

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

    private static final String ADDRESS =
            """
            Address 1:
            Building Number: 851
            Street Name: Bolshaya Pokrovskaya Street
            Street Address: State Bank Building
            State: Nizhny Novgorod Oblast
            City: Nizhny Novgorod
            Post Code: 603005
            """;

    @Test
    void shouldCreateAndRetrieveFacility() {
        FacilityCreateDto request = new FacilityCreateDto("store1", ADDRESS);

        Optional<FacilityFullDto> optionalResponse = facilityService.createFacility(request);
        assertThat(optionalResponse).isPresent();
        FacilityFullDto facility = optionalResponse.get();
        assertThat(facility.id()).isNotNull();
        assertThat(facility.name()).isEqualTo("store1");
        assertThat(facility.address()).isEqualTo(ADDRESS);

        Optional<FacilityFullDto> optionalRetrieved = facilityService.getById(facility.id());
        assertThat(optionalRetrieved).isPresent();
        FacilityFullDto facilityRetrieved = optionalRetrieved.get();

        assertThat(facilityRetrieved.id()).isEqualTo(facility.id());
        assertThat(facilityRetrieved.name()).isEqualTo("store1");
        assertThat(facilityRetrieved.address()).isEqualTo(ADDRESS);
    }
}
