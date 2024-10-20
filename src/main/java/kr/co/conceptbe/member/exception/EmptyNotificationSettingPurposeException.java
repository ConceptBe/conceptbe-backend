package kr.co.conceptbe.member.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyNotificationSettingPurposeException extends ConceptBeException {

    public EmptyNotificationSettingPurposeException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "목적은 최소 한 개 이상 고르셔야 합니다."));
    }

}
