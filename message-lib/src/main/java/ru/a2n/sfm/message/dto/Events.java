package ru.a2n.sfm.message.dto;

import java.util.List;

public class Events {
    private Events() {}

    public static void register() {
        MessageTypes.registerMessageTypes(List.of(
                "EVENTCounterElectricity",
                "EVENTCounterWater",
                "EVENTDoorOpen",
                "EVENTDoorClose",
                "EVENTHumidity",
                "EVENTTemperature"));
    }
}
