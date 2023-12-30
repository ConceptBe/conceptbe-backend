package kr.co.conceptbe.dnd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.conceptbe.common.entity.utils.CommonResponse;
import kr.co.conceptbe.dnd.dto.response.IdeaDetailResponse;
import kr.co.conceptbe.dnd.service.IdeaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/idea")
public class IdeaController {

	private final IdeaService ideaService;

	@GetMapping("/{idea_id}")
	public ResponseEntity<CommonResponse.SingleResponse<IdeaDetailResponse>> getDetailIdeaResponse(
		@PathVariable(name = "idea_id") Long ideaId) {
		return ideaService.getDetailIdeaResponse(ideaId);
	}
}
