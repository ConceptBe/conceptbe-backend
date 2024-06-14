package kr.co.conceptbe.image.domain;

import org.springframework.stereotype.Component;

@Component
public interface IdeaValidator {

    void validateIdea(Long ideaId, Long memberId);

}
