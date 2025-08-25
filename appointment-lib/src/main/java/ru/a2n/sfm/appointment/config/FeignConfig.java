package ru.a2n.sfm.appointment.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public MicrometerCapability micrometerCapability(MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("X-Service-Name", "appointment");
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }
}
