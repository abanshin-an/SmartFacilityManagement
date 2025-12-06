package ru.a2n.sfm.message.dto;

import java.util.List;

public class Alarms {
    public static final String ALARM = "ALARM";

    private Alarms() {}

    public static void register() {
        MessageTypes.registerMessageTypes(List.of("ALARMLeak", "ALARMPowerOutage", "ALARMShortCircuit"));
    }
}
