package ru.a2n.sfm.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum SfmError {
    INVALID_JSON_STRUCTURE("JSON имеет не корректную структуру", HttpStatus.BAD_REQUEST),
    UNKNOWN_ERROR("Непредвиденная ошибка", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("Пользователь с id не найден", HttpStatus.BAD_REQUEST);

    private final String errorMessage;
    private final HttpStatus statusCode;
}
