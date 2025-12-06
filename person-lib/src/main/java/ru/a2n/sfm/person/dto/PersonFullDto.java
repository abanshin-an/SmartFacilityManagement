package ru.a2n.sfm.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

public record PersonFullDto(
        @Schema(
                        name = "id",
                        example = "01988059-b005-7480-82fb-1d64d045bb1e",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                String id,
        @Schema(name = "full_name", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED) String fullName,
        @Schema(name = "login", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED) String login,
        @Schema(name = "email", example = "name@domain.ru", requiredMode = Schema.RequiredMode.REQUIRED) String email,
        @Schema(name = "phone", example = "+79998887777", requiredMode = Schema.RequiredMode.REQUIRED) String phone) {
    public PersonFullDto {
        Objects.requireNonNull(fullName);
        Objects.requireNonNull(login);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phone);
    }
}
