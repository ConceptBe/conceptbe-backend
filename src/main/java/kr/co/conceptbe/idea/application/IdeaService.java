package kr.co.conceptbe.idea.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.branch.domain.persistense.BranchRepository;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.comment.dto.CommentParentResponse;
import kr.co.conceptbe.comment.repository.CommentRepository;
import kr.co.conceptbe.idea.application.request.FilteringRequest;
import kr.co.conceptbe.idea.application.request.IdeaRequest;
import kr.co.conceptbe.idea.application.response.BestIdeaResponse;
import kr.co.conceptbe.idea.application.response.FindIdeaWriteResponse;
import kr.co.conceptbe.idea.application.response.IdeaResponse;
import kr.co.conceptbe.idea.domain.Hit;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaLike;
import kr.co.conceptbe.idea.domain.IdeaLikeID;
import kr.co.conceptbe.idea.domain.persistence.HitRepository;
import kr.co.conceptbe.idea.domain.persistence.IdeaLikesRepository;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.dto.IdeaHitResponse;
import kr.co.conceptbe.idea.exception.IdeaLikeException;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.exception.UnAuthorizedMemberException;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.region.domain.presentation.RegionRepository;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
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
    private final RegionRepository regionRepository;
    private final IdeaRepository ideaRepository;
    private final MemberRepository memberRepository;
    private final IdeaLikesRepository ideaLikesRepository;
    private final HitRepository hitRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final CommentRepository commentRepository;

    public Long save(AuthCredentials authCredentials, IdeaRequest request) {
        validateMember(authCredentials);

        Idea idea = Idea.of(
                request.title(),
                request.introduce(),
                request.cooperationWay(),
                regionRepository.getById(request.recruitmentPlaceId()),
                memberRepository.getById(authCredentials.id()),
                branchRepository.findByIdIn(request.branchIds()),
                purposeRepository.findByIdIn(request.purposeIds()),
                skillCategoryRepository.findByIdIn(request.skillCategoryIds())
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
    public List<BestIdeaResponse> findAllBestIdea(FilteringRequest filteringRequest, Pageable pageable) {
        return ideaRepository.findAllByOrderByLikesDesc(filteringRequest, pageable)
                .stream()
                .map(BestIdeaResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<IdeaResponse> findAll(
            AuthCredentials authCredentials,
            FilteringRequest filteringRequest,
            Pageable pageable
    ) {
        if (Objects.isNull(authCredentials)) {
            return findAllOfGuest(filteringRequest, pageable);
        }

        Member member = memberRepository.getById(authCredentials.id());
        Set<Idea> ideasBookmarkedByMember = getIdeasBookmarkedByMember(member);
        return ideaRepository.findAllByOrderByCreatedAtDesc(filteringRequest, pageable)
                .stream()
                .map(idea -> IdeaResponse.ofMember(idea, ideasBookmarkedByMember.contains(idea)))
                .toList();
    }

    private List<IdeaResponse> findAllOfGuest(FilteringRequest filteringRequest, Pageable pageable) {
        return ideaRepository.findAllByOrderByCreatedAtDesc(filteringRequest, pageable)
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
        IdeaDetailResponse ideaDetailResponse = IdeaDetailResponse.of(tokenMemberId, idea);

        Member member = memberRepository.getById(tokenMemberId);
        Hit hit = Hit.of(member, idea);
        hitRepository.save(hit);

        return ideaDetailResponse;
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
                skillCategoryRepository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public List<CommentParentResponse> getIdeaCommentResponse(Long ideaId, Pageable pageable) {
        Idea idea = ideaRepository.getById(ideaId);
        return commentRepository.findByIdea(idea)
                .stream()
                .filter(Comment::isParentComment)
                .map(CommentParentResponse::from)
                .toList();
    }

    public List<IdeaHitResponse> getIdeaHitsResponse(Long ideaId) {
        Idea idea = ideaRepository.getById(ideaId);

        return idea.getHits().stream()
                .map(Hit::getMember)
                .map(IdeaHitResponse::from)
                .toList();
    }
}
