package kr.co.conceptbe.idea.application;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.common.entity.domain.persistence.BranchRepository;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.common.entity.domain.persistence.TeamRecruitmentRepository;
import kr.co.conceptbe.idea.exception.IdeaLikeException;
import kr.co.conceptbe.idea.domain.IdeaLikeID;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaLike;
import kr.co.conceptbe.idea.domain.persistence.IdeaLikesRepository;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.presentation.dto.response.BestIdeaResponse;
import kr.co.conceptbe.idea.presentation.dto.request.IdeaRequest;
import kr.co.conceptbe.idea.presentation.dto.response.IdeaResponse;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IdeaService {

    private final BranchRepository branchRepository;
    private final PurposeRepository purposeRepository;
    private final TeamRecruitmentRepository teamRecruitmentRepository;
    private final IdeaRepository ideaRepository;
    private final MemberRepository memberRepository;
    private final IdeaLikesRepository ideaLikesRepository;

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

    @Transactional(readOnly = true)
    public List<BestIdeaResponse> findAllBestIdea() {
        return ideaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Idea::getLikesCount).reversed())
                .map(BestIdeaResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<IdeaResponse> findAll(Member member) {
        Set<Idea> ideasBookmarkedByMember = getIdeasBookmarkedByMember(member);

        return ideaRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(idea -> IdeaResponse.of(idea, ideasBookmarkedByMember.contains(idea)))
                .toList();
    }

    private Set<Idea> getIdeasBookmarkedByMember(Member member) {
        return member.getBookmarks()
                .stream()
                .map(Bookmark::getIdea)
                .collect(Collectors.toSet());
    }

    public IdeaDetailResponse getDetailIdeaResponse(Long ideaId) {
        Idea idea = ideaRepository.getById(ideaId);
        return IdeaDetailResponse.from(idea);
    }

    public Long likesIdea(Long tokenMemberId, Long ideaId) {
        Idea idea = ideaRepository.getById(ideaId);
        Member member = memberRepository.getById(tokenMemberId);

        IdeaLikeID ideaLikeID = new IdeaLikeID(tokenMemberId, ideaId);
        Optional<IdeaLike> optionalIdeaLike = ideaLikesRepository.findById(ideaLikeID);

        if (optionalIdeaLike.isEmpty()) {
            IdeaLike ideaLike = new IdeaLike(ideaLikeID, member, idea);
            ideaLikesRepository.save(ideaLike);
            idea.addIdeaLikes(ideaLike);
        } else {
            throw new IdeaLikeException();
        }

        return idea.getId();
    }

    public void likesCancelIdea(Long tokenMemberId, Long ideaId) {
        IdeaLikeID ideaLikeID = new IdeaLikeID(tokenMemberId, ideaId);
        ideaLikesRepository.getById(ideaLikeID);
        ideaLikesRepository.deleteById(ideaLikeID);
    }
}
