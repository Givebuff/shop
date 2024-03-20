package com.kcj.management.shop.model.dto;

import lombok.Getter;

@Getter
public enum RestApiRequestCode {
    // 예시 에러 코드
    SUCCESS(0,"정상적으로 처리되었습니다."),
    VALUE_NOT_FOUND(404, "값을 찾을 수 없습니다."),
    INVALID_PASSWORD(401, "비밀번호가 일치하지 않습니다."),
    SERVER_ERROR(500, "서버 오류입니다.");

    private final int code;
    private final String message;

    RestApiRequestCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
