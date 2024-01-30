package kr.co.conceptbe.teamrecruitment.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundTeamRecruitmentCategoryException extends ConceptBeException {

    public NotFoundTeamRecruitmentCategoryException(Long teamRecruitmentCategoryId) {
        super(new ErrorCode(BAD_REQUEST, format("팀원모집분야 카테고리 아이디가 %s인 팀원모집분야 카테고리를 찾을 수 없습니다.", teamRecruitmentCategoryId)));
    }

}
