package kr.co.conceptbe.skill.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundSkillLevelException extends ConceptBeException {

    public NotFoundSkillLevelException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "존재하지 않는 스킬 레벨입니다. 알맞은 스킬 레벨인지 확인해주세요."));
    }
}
