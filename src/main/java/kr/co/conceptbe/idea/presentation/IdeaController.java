package kr.co.conceptbe.idea.presentation;

import java.net.URI;
import kr.co.conceptbe.idea.application.IdeaService;
import kr.co.conceptbe.idea.presentation.dto.IdeaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idea")
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping
    public ResponseEntity<Void> addIdea(@RequestBody IdeaRequest request) {
        Long savedId = ideaService.save(request);

        return ResponseEntity.created(URI.create("/idea/" + savedId))
                .build();
    }

}
