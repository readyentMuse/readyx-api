package com.readyent.readyx.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 모델
 */
@Getter
@Setter
public class MemberRequestDto {
    @Getter
    @Setter
    public static class InsertRequest {
        @Schema(description = "이름", example = "Daniel", defaultValue = "Daniel", required = true)
        @NotBlank(message = "이름을 확인하세요.")
        private String name;
        @Schema(description = "휴대폰 번호", example = "01012345678", defaultValue = "01012345678", required = true)
        @NotBlank(message = "휴대폰 번호를 확인하세요.")
        @Pattern(regexp = "^[0-9]+$", message = "휴대폰 번호는 숫자만 입력할 수 있습니다.")
        private String mobileNumber;
        @Schema(description = "회사명", example = "레디엔터테인먼트", defaultValue = "레디엔터테인먼트")
        private String companyName;
        @Schema(description = "회사 전화번호", example = "0220380000", defaultValue = "0220380000")
        @Pattern(regexp = "^[0-9]+$", message = "회사 전화번호는 숫자만 입력할 수 있습니다.")
        private String companyPhone;
        @Schema(description = "부서", example = "TMS사업부", defaultValue = "TMS사업부")
        private String department;
        @Schema(description = "이메일", example = "abc@readyent.com", defaultValue = "abc@readyent.com")
        @Email(message = "유효한 이메일 형식이어야 합니다.")
        private String email;
        @Schema(description = "사용 중인 SNS 플랫폼", example = "", defaultValue = "")
        private String snsType;
        @Schema(description = "SNS 제공자가 발급한 고유 사용자", example = "", defaultValue = "")
        private String snsUserId;
        @Schema(description = "명함 파일 데이터 (최대 16M)", required = false)  // 파일이 선택적임을 명시
        private MultipartFile businessCardFileData;
        @Schema(description = "회원 상태: 대기:0001/승인:0002/보류:0003/탈퇴:0009", example = "0001", defaultValue = "0001")
        private String status;

        // 바이트 배열로 변환된 파일 데이터 저장을 위한 필드
        @Schema(hidden = true) // Swagger에서 숨기기
        private byte[] businessCardFileDataAsBytes;

        // 파일 처리 로직 추가
        public void setBusinessCardFileData(MultipartFile businessCardFileData) throws IOException {
            if (businessCardFileData != null && !businessCardFileData.isEmpty()) {
                this.businessCardFileDataAsBytes = businessCardFileData.getBytes();
            }
        }

//        public byte[] getBusinessCardFileDataAsBytes() {
//            return businessCardFileDataAsBytes;
//        }
    }

    @Getter
    @Setter
    public static class FindMemberByDateRequest {
        @Schema(description = "검색 기간 시작일", example = "2024-09-01", defaultValue = "2024-09-01")
        private String startDate;
        @Schema(description = "검색 기간 종료일", example = "2025-12-31", defaultValue = "2025-12-31")
        private String endDate;
    }


    @Getter
    @Setter
    public static class UpdateRequest extends InsertRequest {
    }

    @Getter
    @Setter
    public static class FindMemberByNameAndMobileNumber {
        @Schema(description = "이름", example = "Daniel", defaultValue = "Daniel")
        private String name;
        @Schema(description = "휴대폰번호", example = "01012345678", defaultValue = "01012345678")
        private String mobileNumber;
    }
}
