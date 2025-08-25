package ru.a2n.sfm.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SfmException extends RuntimeException {

    @Getter
    private final SfmError error;

    @Getter
    private final String extraInformation;

    @Override
    public String getMessage() {
        return error + ": " + extraInformation;
    }
}
