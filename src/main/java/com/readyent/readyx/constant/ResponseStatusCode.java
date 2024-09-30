package com.readyent.readyx.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 응답 코드
 * 처리(200)/조회(201)/등록(202)/수정(203)/삭제(204)
 */
@Getter
@AllArgsConstructor
public enum ResponseStatusCode {
    RESPONSE_SUCCESS_FIND(200, "0000", "정상적으로 처리되었습니다."),
    RESPONSE_SUCCESS_EXEC(200, "0001", "정상적으로 조회되었습니다."),
    RESPONSE_SUCCESS_INST(200, "0002", "정상적으로 등록되었습니다."),
    RESPONSE_SUCCESS_UPDT(200, "0003", "정상적으로 수정되었습니다."),
    RESPONSE_SUCCESS_DELL(200, "0004", "정상적으로 삭제되었습니다."),
    RESPONSE_FAIL(500, "9999", "처리 실패");

    private final int status;
    private final String code;
    private final String message;
}
