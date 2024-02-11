package kr.co.conceptbe.idea.presentation;

import java.net.URI;
import java.util.List;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    @PostMapping
    public ResponseEntity<Void> addIdea(
            @Auth AuthCredentials auth,
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
            @OptionalAuth AuthCredentials authCredentials,
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

    @GetMapping("/{ideaId}")
    public ResponseEntity<IdeaDetailResponse> getDetailIdeaResponse(
        @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        IdeaDetailResponse ideaDetailResponse = ideaService.getDetailIdeaResponse(authCredentials.id(), ideaId);
        return ResponseEntity.ok(ideaDetailResponse);
    }

    @GetMapping("/{ideaId}/comments")
    public ResponseEntity<List<CommentParentResponse>> getIdeaCommentResponse(
        @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        List<CommentParentResponse> commentParentResponses = ideaService.getIdeaCommentResponse(ideaId);
        return ResponseEntity.ok(commentParentResponses);
    }

    @PostMapping("/likes/{ideaId}")
    public ResponseEntity<Void> likesIdea(
        @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        Long id = ideaService.likesIdea(authCredentials.id(), ideaId);
        return ResponseEntity.created(URI.create("/ideas/" + id))
            .build();
    }

    @DeleteMapping("/likes/{ideaId}")
    public ResponseEntity<Void> likesCancelIdea(
        @Auth AuthCredentials authCredentials,
        @PathVariable(name = "ideaId") Long ideaId) {
        ideaService.likesCancelIdea(authCredentials.id(), ideaId);
        return ResponseEntity.noContent().build();
    }
}
