package kr.co.conceptbe.idea.application;

import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import kr.co.conceptbe.common.entity.domain.persistence.BranchRepository;
import kr.co.conceptbe.common.entity.domain.persistence.PurposeRepository;
import kr.co.conceptbe.common.entity.domain.persistence.TeamRecruitmentRepository;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.idea.presentation.dto.BestIdeaResponse;
import kr.co.conceptbe.idea.presentation.dto.IdeaRequest;
import kr.co.conceptbe.idea.presentation.dto.IdeaResponse;
import kr.co.conceptbe.member.Member;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IdeaService {

    private final BranchRepository branchRepository;
    private final PurposeRepository purposeRepository;
    private final TeamRecruitmentRepository teamRecruitmentRepository;
    private final IdeaRepository ideaRepository;

    public IdeaService(
            BranchRepository branchRepository,
            PurposeRepository purposeRepository,
            TeamRecruitmentRepository teamRecruitmentRepository,
            IdeaRepository ideaRepository
    ) {
        this.branchRepository = branchRepository;
        this.purposeRepository = purposeRepository;
        this.teamRecruitmentRepository = teamRecruitmentRepository;
        this.ideaRepository = ideaRepository;
    }

    public Long save(Member member, IdeaRequest request) {
        Idea idea = Idea.of(
                request.title(),
                request.introduce(),
                request.cooperationWay(),
                request.recruitmentPlace(),
                member,
                branchRepository.findByIdIn(request.branchIds()),
                purposeRepository.findByIdIn(request.purposeIds()),
                teamRecruitmentRepository.findByIdIn(request.teamRecruitmentIds())
        );

        return ideaRepository.save(idea).getId();
    }

    public List<BestIdeaResponse> findAllBestIdea() {
        return ideaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Idea::getLikesCount).reversed())
                .map(BestIdeaResponse::from)
                .toList();
    }

    public List<IdeaResponse> findAll() {
        return null;
    }

}
