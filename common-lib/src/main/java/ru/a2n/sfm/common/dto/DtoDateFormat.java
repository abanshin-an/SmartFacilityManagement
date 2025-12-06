package ru.a2n.sfm.common.dto;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public final class DtoDateFormat {
    private DtoDateFormat() {}

    public static final String DESERIALIZATION_ISO_INSTANT =
            "yyyy-MM-dd[['T'][HH][:mm][:ss][.[SSSSSSSSS][SSSSSSSS][SSSSSSS][SSSSSS][SSSSS][SSSS][SSS][SS][S]]['Z']";
    public static final DateTimeFormatter DESERIALIZATION_ISO_INSTANT_FORMATTER = (new DateTimeFormatterBuilder())
            .appendPattern(DESERIALIZATION_ISO_INSTANT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0L)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0L)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0L)
            .toFormatter();
    public static final String SERIALIZATION_ISO_INSTANT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final DateTimeFormatter SERIALIZATION_ISO_INSTANT_FORMATTER =
            DateTimeFormatter.ofPattern(SERIALIZATION_ISO_INSTANT);
    public static final String SERIALIZATION_ISO_DATE = "yyyy-MM-dd";
    public static final DateTimeFormatter SERIALIZATION_ISO_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(SERIALIZATION_ISO_DATE);
}
