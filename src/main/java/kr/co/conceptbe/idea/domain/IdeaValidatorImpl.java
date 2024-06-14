package kr.co.conceptbe.idea.domain;

import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.image.domain.IdeaValidator;
import kr.co.conceptbe.member.exception.NotOwnerException;
import org.springframework.stereotype.Component;

@Component
public class IdeaValidatorImpl implements IdeaValidator {

    private final IdeaRepository ideaRepository;

    public IdeaValidatorImpl(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @Override
    public void validateIdea(Long ideaId, Long memberId) {
        Idea savedIdea = ideaRepository.getById(ideaId);
        if (savedIdea.isOwner(memberId)) {
            return;
        }
        throw new NotOwnerException(memberId);
    }

}
