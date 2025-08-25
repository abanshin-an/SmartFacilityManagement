package ru.a2n.sfm.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Schema(description = "Request for password change for user with id")
@Jacksonized
@Builder
public record ChangePasswordDto(
        @Schema(
                        name = "id",
                        example = "01988059-b005-7480-82fb-1d64d045bb1e",
                        requiredMode = Schema.RequiredMode.REQUIRED)
                String id,
        @Schema(name = "login", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED) String login,
        @Schema(name = "oldPassword", example = "0ldP@$$w0rd", requiredMode = Schema.RequiredMode.REQUIRED)
                String oldPassword,
        @Schema(name = "newPassword", example = "#ewP@$$w0rd", requiredMode = Schema.RequiredMode.REQUIRED)
                String newPassword) {
    public ChangePasswordDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(login);
        Objects.requireNonNull(newPassword);
    }
}
