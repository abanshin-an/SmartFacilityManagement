package ru.a2n.sfm.common.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.a2n.sfm.common.dto.DtoDateFormat;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        LocalDateTimeDeserializer dateTimeDeserializer =
                new LocalDateTimeDeserializer(DtoDateFormat.DESERIALIZATION_ISO_INSTANT_FORMATTER);
        LocalDateTimeSerializer dateTimeSerializer =
                new LocalDateTimeSerializer(DtoDateFormat.SERIALIZATION_ISO_INSTANT_FORMATTER);
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);
        timeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        LocalDateDeserializer dateDeserializer =
                new LocalDateDeserializer(DtoDateFormat.DESERIALIZATION_ISO_INSTANT_FORMATTER);
        LocalDateSerializer dateSerializer = new LocalDateSerializer(DtoDateFormat.SERIALIZATION_ISO_DATE_FORMATTER);
        timeModule.addSerializer(LocalDate.class, dateSerializer);
        timeModule.addDeserializer(LocalDate.class, dateDeserializer);
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(timeModule);
    }
}
