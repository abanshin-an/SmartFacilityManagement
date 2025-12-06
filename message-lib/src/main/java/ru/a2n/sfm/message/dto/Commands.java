package ru.a2n.sfm.message.dto;

import java.util.List;

public class Commands {
    private Commands() {}

    public static void register() {
        MessageTypes.registerMessageTypes(List.of(
                "COMMANDCloseCurtain",
                "COMMANDTurnOffTheWater",
                "COMMANDSetTheLockCode",
                "COMMANDTurnOffTheElectricity",
                "COMMANDNotifyResponsible",
                "COMMANDNotifyOwner"));
    }
}
