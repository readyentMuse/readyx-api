package com.readyent.readyx.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 모델
 */
@Getter
@Setter
public class MemberResponseDto {
    @Getter
    @Setter
    public static class GetResponse {
        @Schema(description = "회원번호", example = "1", defaultValue = "1")
        private Integer idx;
        @Schema(description = "이름", example = "Daniel", defaultValue = "Daniel")
        private String name;
        @Schema(description = "휴대폰 번호", example = "01012345678", defaultValue = "01012345678")
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
        @Schema(description = "명함 파일 데이터 (최대 16M)", example = "PNG        IHDR   @   @     êøNa    gAMA  ±  üa     iCCPicc  H µVy< k ~Þ÷ }±Í ÝØ·F 0È¾ ÈN 13 Ë`Ì Ò&©p\"I¶ 9 :tZ Ó\"-Ú Ò¦¢ÎÈ ªÓÑ", defaultValue = "PNG        IHDR   @   @     êøNa    gAMA  ±  üa     iCCPicc  H µVy< k ~Þ÷ }±Í ÝØ·F 0È¾ ÈN 13 Ë`Ì Ò&©p\"I¶ 9 :tZ Ó\"-Ú Ò¦¢ÎÈ ªÓÑ")
        private byte[] businessCardFile;
        @Schema(description = "회원 상태: 대기:0001/승인:0002/보류:0003/탈퇴:0009", example = "0001", defaultValue = "0001")
        private String status;
        @Schema(description = "승인일", example = "2024-09-25 10:57:04", defaultValue = "2024-09-25 10:57:04")
        private LocalDateTime approvedAt;
        @Schema(description = "생성일", example = "2024-09-25 09:57:04", defaultValue = "2024-09-25 09:57:04")
        private LocalDateTime createdAt;
        @Schema(description = "수정일", example = "2024-09-26 10:57:04", defaultValue = "2024-09-26 10:57:04")
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    public static class GetStatusGroupCountResponse {
        @Schema(description = "회원 상태: 대기:0001/승인:0002/보류:0003/탈퇴:0009", example = "0001", defaultValue = "0001")
        private String status;
        @Schema(description = "갯수", example = "7", defaultValue = "7")
        private Integer totalCount;
    }

    @Getter
    @Setter
    @Builder
    public static class FindMemberByStatuseResponse {
        PaginationResponseDto.TotalCountResponse totalCount;
        List<GetResponse> memberList;
    }

    @Getter
    @Setter
    @Builder
    public static class FindMemberByDateResponse {
        List<GetResponse> memberList;
    }

    @Getter
    @Setter
    @Builder
    public static class FindMemberByNameAndMobileNumberResponse {
        PaginationResponseDto.TotalCountResponse totalCount;
        List<GetResponse> memberList;
    }

    @Getter
    @Setter
    @Builder
    public static class FindMemberByNameAndMobileNumberAndStatuseResponse {
        PaginationResponseDto.TotalCountResponse totalCount;
        List<GetResponse> memberList;
    }
}
