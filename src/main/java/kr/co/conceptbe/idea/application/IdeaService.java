package kr.co.conceptbe.idea.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.branch.domain.persistense.BranchRepository;
import kr.co.conceptbe.idea.application.response.FindIdeaWriteResponse;
import kr.co.conceptbe.member.exception.UnAuthorizedMemberException;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.region.domain.presentation.RegionRepository;
import kr.co.conceptbe.teamrecruitment.domain.persistence.TeamRecruitmentCategoryRepository;
import kr.co.conceptbe.teamrecruitment.domain.persistence.TeamRecruitmentRepository;
import kr.co.conceptbe.idea.exception.IdeaLikeException;
import kr.co.conceptbe.idea.domain.IdeaLikeID;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaLike;
import kr.co.conceptbe.idea.domain.persistence.IdeaLikesRepository;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.application.response.BestIdeaResponse;
import kr.co.conceptbe.idea.application.request.IdeaRequest;
import kr.co.conceptbe.idea.application.response.IdeaResponse;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IdeaService {

    private final BranchRepository branchRepository;
    private final PurposeRepository purposeRepository;
    private final TeamRecruitmentCategoryRepository teamRecruitmentCategoryRepository;
    private final TeamRecruitmentRepository teamRecruitmentRepository;
    private final RegionRepository regionRepository;
    private final IdeaRepository ideaRepository;
    private final MemberRepository memberRepository;
    private final IdeaLikesRepository ideaLikesRepository;

    public Long save(AuthCredentials authCredentials, IdeaRequest request) {
        validateMember(authCredentials);
        Idea idea = Idea.of(
                request.title(),
                request.introduce(),
                request.cooperationWay(),
                request.recruitmentPlace(),
                memberRepository.getById(authCredentials.id()),
                branchRepository.findByIdIn(request.branchIds()),
                purposeRepository.findByIdIn(request.purposeIds()),
                teamRecruitmentRepository.findByIdIn(request.teamRecruitmentIds())
        );

        return ideaRepository.save(idea).getId();
    }

    private void validateMember(AuthCredentials authCredentials) {
        if (Objects.nonNull(authCredentials)) {
            return;
        }

        throw new UnAuthorizedMemberException();
    }

    @Transactional(readOnly = true)
    public List<BestIdeaResponse> findAllBestIdea(Pageable pageable) {
        return ideaRepository.findAllByOrderByLikesDesc(pageable)
                .stream()
                .map(BestIdeaResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<IdeaResponse> findAll(AuthCredentials authCredentials, Pageable pageable) {
        if (Objects.isNull(authCredentials)) {
            return findAllOfGuest(pageable);
        }

        Member member = memberRepository.getById(authCredentials.id());
        Set<Idea> ideasBookmarkedByMember = getIdeasBookmarkedByMember(member);
        return ideaRepository.findAllByOrderByCreatedAtDesc(pageable)
                .stream()
                .map(idea -> IdeaResponse.ofMember(idea, ideasBookmarkedByMember.contains(idea)))
                .toList();
    }

    private List<IdeaResponse> findAllOfGuest(Pageable pageable) {
        return ideaRepository.findAllByOrderByCreatedAtDesc(pageable)
                .stream()
                .map(IdeaResponse::ofGuest)
                .toList();
    }

    private Set<Idea> getIdeasBookmarkedByMember(Member member) {
        return member.getBookmarks()
                .stream()
                .map(Bookmark::getIdea)
                .collect(Collectors.toSet());
    }

    public IdeaDetailResponse getDetailIdeaResponse(Long tokenMemberId, Long ideaId) {
        Idea idea = ideaRepository.getById(ideaId);
        return IdeaDetailResponse.of(tokenMemberId, idea);
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

    public FindIdeaWriteResponse getFindIdeaWriteResponse() {
        return FindIdeaWriteResponse.of(
                regionRepository.findAll(),
                branchRepository.findAll(),
                purposeRepository.findAll(),
                teamRecruitmentCategoryRepository.findAll()
        );
    }

}
