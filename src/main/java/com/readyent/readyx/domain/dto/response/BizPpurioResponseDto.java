package com.readyent.readyx.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 비즈 뿌리오
 */
@Getter
@Setter
public class BizPpurioResponseDto {
    @Getter
    @Setter
    @Builder
    public static class TokenResponse{
        @Schema(description = "일련번호", example = "1", defaultValue = "1")
        private Long id;
        @Schema(description = "토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50IjoiSlY1VXk0ZFJXN0cvTktuc0tUWDBKZz09IiwiYWxsb3dlZElwIjoibkg0amVNSkZHU2EyRitPM3VPRDlpVmxteFFPTXZ4eUw3QWRSZDFlQmxCSlNFZ3lZSjZCMXEvODB2aDJNZzRlVnYyS2JGSFNCT0VYMkR0RnFSZVFweCt4Y1ZmMlJZL1JwTUFHY0lla3R3eGlPSFlYU1VDN0ZpajBER2hTZFZmK1BJb01GTEJKSFUrZlNqUzhrVElhTm9RPT0iLCJyYXRlTGltaXQiOiI5OTk5IiwiaWF0IjoxNzI4MzUwMDY0LCJleHAiOjE3Mjg0MzY0NjQsImlzcyI6ImFwaS5iaXpwcHVyaW8uY29tIn0.5bBh7JxME5xd5GJG_RWVQQc7tGLUVIc5-_fxrviW2eI", defaultValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50IjoiSlY1VXk0ZFJXN0cvTktuc0tUWDBKZz09IiwiYWxsb3dlZElwIjoibkg0amVNSkZHU2EyRitPM3VPRDlpVmxteFFPTXZ4eUw3QWRSZDFlQmxCSlNFZ3lZSjZCMXEvODB2aDJNZzRlVnYyS2JGSFNCT0VYMkR0RnFSZVFweCt4Y1ZmMlJZL1JwTUFHY0lla3R3eGlPSFlYU1VDN0ZpajBER2hTZFZmK1BJb01GTEJKSFUrZlNqUzhrVElhTm9RPT0iLCJyYXRlTGltaXQiOiI5OTk5IiwiaWF0IjoxNzI4MzUwMDY0LCJleHAiOjE3Mjg0MzY0NjQsImlzcyI6ImFwaS5iaXpwcHVyaW8uY29tIn0.5bBh7JxME5xd5GJG_RWVQQc7tGLUVIc5-_fxrviW2eI")
        private String accesstoken;
        @Schema(description = "타입", example = "Bearer", defaultValue = "Bearer")
        private String type;
        @Schema(description = "만료 시점", example = "20241009101424", defaultValue = "20241009101424")
        private String expired;
        @Schema(description = "생성일", example = "2024-10-08 09:57:04", defaultValue = "2024-10-08 09:57:04")
        private LocalDateTime createAt;
    }

    /**
     * 뿌리오 발송 후 응답
     */
    @Getter
    @Setter
    @Builder
    public static class GetVerificationResponse{
        @Schema(description = "모바일 전화번호", example = "01012345678", defaultValue = "01012345678")
        private String mobileNumber;
        @Schema(description = "'인증번호'", example = "953394", defaultValue = "953394")
        private String verificationCode;
        @Schema(description = "code", example = "1000", defaultValue = "1000")
        private Integer code;
        @Schema(description = "description", example = "success", defaultValue = "success")
        private String description;
        @Schema(description = "refkey", example = "readyx", defaultValue = "readyx")
        private String refkey;
        @Schema(description = "messagekey", example = "241008170259310sms018623museOiiV", defaultValue = "241008170259310sms018623museOiiV")
        private String messagekey;
        @Schema(description = "생성일", example = "2024-10-08 09:57:04", defaultValue = "2024-10-08 09:57:04")
        private LocalDateTime createAt;
    }

}
