package com.readyent.readyx.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    @Schema(description = "User's ID", example = "1")
    private Long id;

    @Schema(description = "User's name", example = "John Doe")
    private String name;
    @Schema(description = "User's email", example = "abc@readyent.com")
    private String email;
}
