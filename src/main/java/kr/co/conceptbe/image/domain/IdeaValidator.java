package kr.co.conceptbe.image.domain;

import org.springframework.stereotype.Service;

@Service
public interface IdeaValidator {

    boolean existsIdea(Long ideaId);

}
