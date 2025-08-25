package ru.a2n.sfm.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.a2n.sfm.person.repository")
@EntityScan(basePackages = "ru.a2n.sfm.person.model")
public class PersonApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonApplication.class, args);
    }
}
