package ru.a2n.sfm.action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients(basePackages = "ru.a2n.sfm.appointment")
@EnableScheduling
public class ActionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActionApplication.class, args);
    }
}
