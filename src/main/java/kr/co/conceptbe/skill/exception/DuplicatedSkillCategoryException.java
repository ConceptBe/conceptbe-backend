package kr.co.conceptbe.skill.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class DuplicatedSkillCategoryException extends ConceptBeException {

    public DuplicatedSkillCategoryException() {
        super(new ErrorCode(BAD_REQUEST, "중복된 스킬을 가질 수 없습니다."));
    }
}
