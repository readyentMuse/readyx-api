package com.readyent.readyx.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthRequestDto {
    @Getter
    @Setter
    @Builder
    public static class SendVerificationCodeResponse {
        @Schema(description = "결과 내용", example = "발송되었습니다.", defaultValue = "발송되었습니다.")
        private String massage;
    }
}
