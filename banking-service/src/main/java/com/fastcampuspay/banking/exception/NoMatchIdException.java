package com.fastcampuspay.banking.exception;

public class NoMatchIdException extends RuntimeException {

    public NoMatchIdException() {
        super("멤버쉽 ID가 일치하지 않습니다.");
    }
}
