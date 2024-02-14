package kr.co.conceptbe.idea.presentation;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.comment.dto.CommentParentResponse;
import kr.co.conceptbe.common.auth.Auth;
import kr.co.conceptbe.common.auth.OptionalAuth;
import kr.co.conceptbe.idea.application.IdeaService;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.application.request.IdeaRequest;
import kr.co.conceptbe.idea.application.response.BestIdeaResponse;
import kr.co.conceptbe.idea.application.response.FindIdeaWriteResponse;
import kr.co.conceptbe.idea.application.response.IdeaResponse;
import kr.co.conceptbe.idea.dto.IdeaHitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ideas")
@Tag(name = "Idea", description = "Idea API")
@SecurityRequirement(name = AUTHORIZATION)
public class IdeaController {

    private final IdeaService ideaService;

    @PostMapping
    public ResponseEntity<Void> addIdea(
        @Parameter(hidden = true) @Auth AuthCredentials auth,
        @RequestBody IdeaRequest request
    ) {
        Long savedId = ideaService.save(auth, request);

        return ResponseEntity.created(URI.create("/ideas/" + savedId))
                .build();
    }

    @GetMapping("writing")
    public ResponseEntity<FindIdeaWriteResponse> getIdeaWriteResponses() {
        FindIdeaWriteResponse response = ideaService.getFindIdeaWriteResponse();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<IdeaResponse>> findAll(
        @Parameter(hidden = true) @OptionalAuth AuthCredentials authCredentials,
        @RequestParam int page,
        @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<IdeaResponse> responses = ideaService.findAll(authCredentials, pageable);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/best")
    public ResponseEntity<List<BestIdeaResponse>> findBestIdeas(
        @RequestParam int page,
        @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<BestIdeaResponse> responses = ideaService.findAllBestIdea(pageable);

        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Idea 상세 조회", description = "피드글의 상세 내용을 가져옵니다.")
    @GetMapping("/{ideaId}")
    public ResponseEntity<IdeaDetailResponse> getDetailIdeaResponse(
        @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        IdeaDetailResponse ideaDetailResponse = ideaService.getDetailIdeaResponse(authCredentials.id(), ideaId);
        return ResponseEntity.ok(ideaDetailResponse);
    }

    @Operation(summary = "Idea 상세 댓글 조회", description = "피드글의 댓글을 가져옵니다.")
    @GetMapping("/{ideaId}/comments")
    public ResponseEntity<List<CommentParentResponse>> getIdeaCommentResponse(
        @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        List<CommentParentResponse> commentParentResponses = ideaService.getIdeaCommentResponse(ideaId);
        return ResponseEntity.ok(commentParentResponses);
    }

    @Operation(summary = "Idea 좋아요", description = "피드글을 좋아요를 합니다.")
    @PostMapping("/likes/{ideaId}")
    public ResponseEntity<Void> likesIdea(
        @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        Long id = ideaService.likesIdea(authCredentials.id(), ideaId);
        return ResponseEntity.created(URI.create("/ideas/" + id))
            .build();
    }

    @Operation(summary = "Idea 좋아요 취소", description = "피드글을 좋아요 취소를 합니다.")
    @PatchMapping("/likes/{ideaId}")
    public ResponseEntity<Void> likesCancelIdea(
        @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        ideaService.likesCancelIdea(authCredentials.id(), ideaId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Idea 조회한 사람 확인", description = "피드글을 조회한 사람들의 목록을 가져옵니다.")
    @GetMapping("/{ideaId}/hits")
    public ResponseEntity<List<IdeaHitResponse>> getIdeaHitsResponse(
        @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        List<IdeaHitResponse> ideaCommentResponse = ideaService.getIdeaHitsResponse(ideaId);
        return ResponseEntity.ok(ideaCommentResponse);
    }
}
