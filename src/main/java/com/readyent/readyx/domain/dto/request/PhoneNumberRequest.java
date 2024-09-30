package com.readyent.readyx.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberRequest {
    @Schema(description = "휴대폰 번호", example = "01012345678", defaultValue = "01012345678", required = true)
    @NotBlank(message = "휴대폰 번호를 확인하세요.")
    @Pattern(regexp = "^[0-9]+$", message = "휴대폰 번호는 숫자만 입력할 수 있습니다.")
    private String phoneNumber;
}
