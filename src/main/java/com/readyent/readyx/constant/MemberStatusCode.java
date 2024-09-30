package com.readyent.readyx.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 상태
 */
@Getter
@AllArgsConstructor
public enum MemberStatusCode {
    PENDING(200, "0001", "대기"),
    APPROVED(200, "0002", "승인"),
    HOLD(200,"0003", "보류"),
    WITHDRAWN(200,"0009", "탈퇴");

    private final int status;
    private final String code;
    private final String description;
}
