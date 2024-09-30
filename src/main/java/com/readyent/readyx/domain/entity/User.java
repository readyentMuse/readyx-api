package com.readyent.readyx.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private Long id;
    private String name;
    @Schema(description = "User's email", example = "user@example.com", defaultValue = "unknown@example.com")
    private String email;
}
