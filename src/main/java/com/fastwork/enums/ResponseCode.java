package com.fastwork.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    NOT_FOUND("NOT_FOUND"),
    USER_IS_DELETE("USER_IS_DELETE"),
    TOKEN_HAS_EXPIRED("TOKEN_HAS_EXPIRED"),
    USER_NOT_AUTHENTICATED("USER_NOT_AUTHENTICATED"),
    EMAIL_NOT_CONFIRMED("EMAIL_NOT_CONFIRMED");
    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }
}
