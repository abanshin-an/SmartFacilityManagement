package ru.a2n.sfm.action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "ru.a2n.sfm.appointment")
public class ActionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActionApplication.class, args);
    }
}
