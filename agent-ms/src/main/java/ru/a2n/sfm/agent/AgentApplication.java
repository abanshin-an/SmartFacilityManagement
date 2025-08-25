package ru.a2n.sfm.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.JvmMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.LogbackMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"ru.a2n.sfm.*.configuration", "ru.a2n.sfm.agent.inbox", "ru.a2n.sfm.agent.outbox"},
        exclude = {
            JvmMetricsAutoConfiguration.class,
            LogbackMetricsAutoConfiguration.class,
            MetricsAutoConfiguration.class
        })
public class AgentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class);
    }
}
