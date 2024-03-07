package com.Discipline.cinehub.exception;

import lombok.Getter;
public enum ExceptionCode {

    USER_NOT_FOUND(404, "User Not Found"),
    UNABLE_TO_SEND_EMAIL(500, "Email sending failed"),
    USER_EXISTS(409, "User exists"),
    NO_SUCH_ALGORITHM(400, "No such algorithm");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
