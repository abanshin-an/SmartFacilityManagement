package ru.a2n.sfm.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SfmErrorRepresentation {

    private String errorCode;

    private String errorName;

    private String extraInformation;
}
