package ru.a2n.sfm.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

public record PersonCreateDto(
        @Schema(name = "full_name", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED) String fullName,
        @Schema(name = "login", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED) String login,
        @Schema(name = "email", example = "name@domain.ru", requiredMode = Schema.RequiredMode.REQUIRED) String email,
        @Schema(name = "phone", example = "+79998887777", requiredMode = Schema.RequiredMode.REQUIRED) String phone,
        @Schema(name = "password", example = "P@$$w0rd", requiredMode = Schema.RequiredMode.REQUIRED) String password) {
    public PersonCreateDto {
        Objects.requireNonNull(fullName);
        Objects.requireNonNull(login);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phone);
        Objects.requireNonNull(password);
    }
}
