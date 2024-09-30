package com.readyent.readyx.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Tag(name = "응답", description = "응답")
public class ResponseDto {
    /**
     * 응답 결과
     */
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @Schema(name = "ResultResponse", description = "RESPONSE_SUCCESS_FIND(200, \"0000\", \"정상적으로 처리되었습니다.\"),\n" +
            "    RESPONSE_SUCCESS_EXEC(200, \"0001\", \"정상적으로 조회되었습니다.\"),\n" +
            "    RESPONSE_SUCCESS_INST(200, \"0002\", \"정상적으로 등록되었습니다.\"),\n" +
            "    RESPONSE_SUCCESS_UPDT(200, \"0003\", \"정상적으로 수정되었습니다.\"),\n" +
            "    RESPONSE_SUCCESS_DELL(200, \"0004\", \"정상적으로 삭제되었습니다.\"),\n" +
            "    RESPONSE_FAIL(500, \"9999\", \"처리 실패\")")
    public static class ResultResponse {
        @Schema(description = "상태", example = "200")
        private int status;
        @Schema(description = "Code", example = "0002")
        private String code;
        @Schema(description = "메시지", example = "정상적으로 처리되었습니다.")
        private String message;
    }
}
