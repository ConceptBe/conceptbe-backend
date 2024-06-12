package kr.co.conceptbe.idea.domain;

import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.image.domain.IdeaValidator;
import org.springframework.stereotype.Component;

@Component
public class IdeaValidatorImpl implements IdeaValidator {

    private final IdeaRepository ideaRepository;

    public IdeaValidatorImpl(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @Override
    public boolean existsIdea(Long ideaId) {
        return ideaRepository.existsById(ideaId);
    }

}
