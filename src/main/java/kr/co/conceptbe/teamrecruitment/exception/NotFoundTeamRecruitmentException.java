package kr.co.conceptbe.teamrecruitment.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundTeamRecruitmentException extends ConceptBeException {

    public NotFoundTeamRecruitmentException(Long teamRecruitmentId) {
        super(new ErrorCode(BAD_REQUEST, format("팀원모집분야 아이디가 %s인 팀원모집분야를 찾을 수 없습니다.", teamRecruitmentId)));
    }

}
