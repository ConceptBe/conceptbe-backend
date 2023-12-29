package kr.co.conceptbe.common.exception;

import lombok.Getter;

@Getter
public class ConceptBeException extends RuntimeException {

    private final ErrorCode errorCode;

    public ConceptBeException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }
}
