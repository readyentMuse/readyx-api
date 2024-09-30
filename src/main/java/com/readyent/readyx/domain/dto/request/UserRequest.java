package com.readyent.readyx.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @Schema(description = "The ID of the user", example = "12345")
    private Long id;

    @Schema(description = "The name of the user", example = "Jane Doe", defaultValue = "John Doe")
    @NotNull
    @NotEmpty
    private String name;

    @Schema(description = "이메일", example = "abc@readyent.com", defaultValue = "abc@readyent.com")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;
}
