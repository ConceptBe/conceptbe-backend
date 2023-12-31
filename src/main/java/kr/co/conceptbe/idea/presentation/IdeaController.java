package kr.co.conceptbe.idea.presentation;

import java.net.URI;
import java.util.List;

import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.common.auth.Auth;
import kr.co.conceptbe.idea.application.IdeaService;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.presentation.dto.request.IdeaRequest;
import kr.co.conceptbe.idea.presentation.dto.response.BestIdeaResponse;
import kr.co.conceptbe.idea.presentation.dto.response.IdeaResponse;
import kr.co.conceptbe.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    @PostMapping
    public ResponseEntity<Void> addIdea(
            @RequestBody Member member,
            @RequestBody IdeaRequest request
    ) {
        Long savedId = ideaService.save(member, request);

        return ResponseEntity.created(URI.create("/ideas/" + savedId))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<IdeaResponse>> findAll(
            @RequestBody Member member
    ) {
        List<IdeaResponse> responses = ideaService.findAll(member);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/bests")
    public ResponseEntity<List<BestIdeaResponse>> findBestsIdea() {
        List<BestIdeaResponse> responses = ideaService.findAllBestIdea();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{idea_id}")
    public ResponseEntity<IdeaDetailResponse> getDetailIdeaResponse(
        @PathVariable(name = "idea_id") Long ideaId) {
        IdeaDetailResponse ideaDetailResponse = ideaService.getDetailIdeaResponse(ideaId);
        return ResponseEntity.ok(ideaDetailResponse);
    }

    @PostMapping("/likes/{idea_id}")
    public ResponseEntity<Void> likesIdea(
        @Auth AuthCredentials authCredentials,
        @PathVariable(name = "idea_id") Long ideaId) {
        Long id = ideaService.likesIdea(authCredentials.id(), ideaId);
        return ResponseEntity.created(URI.create("/ideas/" + id))
            .build();
    }

    @DeleteMapping("/likes/{idea_id}")
    public ResponseEntity<Void> likesCancelIdea(
        @Auth AuthCredentials authCredentials,
        @PathVariable(name = "idea_id") Long ideaId) {
        ideaService.likesCancelIdea(authCredentials.id(), ideaId);
        return ResponseEntity.noContent().build();
    }
}
