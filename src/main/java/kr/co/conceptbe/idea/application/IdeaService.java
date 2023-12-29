package kr.co.conceptbe.idea.application;

import jakarta.transaction.Transactional;
import kr.co.conceptbe.common.entity.domain.persistence.BranchRepository;
import kr.co.conceptbe.common.entity.domain.persistence.PurposeRepository;
import kr.co.conceptbe.common.entity.domain.persistence.TeamRecruitmentRepository;
import kr.co.conceptbe.idea.presentation.dto.IdeaRequest;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class IdeaService {

    private final BranchRepository branchRepository;
    private final PurposeRepository purposeRepository;
    private final TeamRecruitmentRepository teamRecruitmentRepository;

    public IdeaService(
            BranchRepository branchRepository,
            PurposeRepository purposeRepository,
            TeamRecruitmentRepository teamRecruitmentRepository
    ) {
        this.branchRepository = branchRepository;
        this.purposeRepository = purposeRepository;
        this.teamRecruitmentRepository = teamRecruitmentRepository;
    }

    public Long save(IdeaRequest request) {
        return null;
    }

}
