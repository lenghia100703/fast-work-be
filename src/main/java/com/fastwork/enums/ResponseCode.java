package com.fastwork.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    NOT_FOUND("NOT_FOUND"),
    USER_IS_DELETE("USER_IS_DELETE"),
    EMAIL_NOT_CONFIRMED("EMAIL_NOT_CONFIRMED");
    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }
}
