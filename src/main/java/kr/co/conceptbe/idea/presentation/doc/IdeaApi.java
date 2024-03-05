package kr.co.conceptbe.idea.presentation.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.comment.dto.CommentParentResponse;
import kr.co.conceptbe.common.auth.Auth;
import kr.co.conceptbe.common.auth.OptionalAuth;
import kr.co.conceptbe.idea.application.request.IdeaRequest;
import kr.co.conceptbe.idea.application.response.BestIdeaResponse;
import kr.co.conceptbe.idea.application.response.FindIdeaWriteResponse;
import kr.co.conceptbe.idea.application.response.IdeaResponse;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.dto.IdeaHitResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Idea", description = "게시글 API")
public interface IdeaApi {

    @Operation(summary = "게시글 작성")
    ResponseEntity<Void> addIdea(
            @Parameter(hidden = true) @Auth AuthCredentials auth,
            @RequestBody IdeaRequest request
    );

    @Operation(summary = "게시글 작성 정보 조회")
    ResponseEntity<FindIdeaWriteResponse> getIdeaWriteResponses();

    @Operation(summary = "최근 게시글 조회(필터링 포함)")
    ResponseEntity<List<IdeaResponse>> findAll(
            @Parameter(hidden = true) @OptionalAuth AuthCredentials authCredentials,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) List<Long> branchIds,
            @RequestParam(required = false) List<Long> purposeIds,
            @RequestParam(required = false) String cooperationWay,
            @RequestParam(required = false) Long region,
            @RequestParam(required = false) List<Long> skillCategoryIds
    );

    @Operation(summary = "인기 게시글 조회(필터링 포함")
    ResponseEntity<List<BestIdeaResponse>> findBestIdeas(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) List<Long> branchIds,
            @RequestParam(required = false) List<Long> purposeIds,
            @RequestParam(required = false) String cooperationWay,
            @RequestParam(required = false) Long region,
            @RequestParam(required = false) List<Long> skillCategoryIds
    );

    @Operation(summary = "Idea 상세 조회", description = "피드글의 상세 내용을 가져옵니다.")
    ResponseEntity<IdeaDetailResponse> getDetailIdeaResponse(
            @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
            @PathVariable(name = "ideaId") Long ideaId
    );

    @Operation(summary = "Idea 상세 댓글 조회", description = "피드글의 댓글을 가져옵니다.")
    ResponseEntity<List<CommentParentResponse>> getIdeaCommentResponse(
            @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
            @RequestParam int page,
            @RequestParam int size,
            @PathVariable(name = "ideaId") Long ideaId
    );

    @Operation(summary = "Idea 좋아요", description = "피드글을 좋아요를 합니다.")
    ResponseEntity<Void> likesIdea(
            @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
            @PathVariable(name = "ideaId") Long ideaId
    );

    @Operation(summary = "Idea 좋아요 취소", description = "피드글을 좋아요 취소를 합니다.")
    ResponseEntity<Void> likesCancelIdea(
            @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
            @PathVariable(name = "ideaId") Long ideaId
    );

    @Operation(summary = "Idea 조회한 사람 확인", description = "피드글을 조회한 사람들의 목록을 가져옵니다.")
    ResponseEntity<List<IdeaHitResponse>> getIdeaHitsResponse(
            @Parameter(hidden = true) @Auth AuthCredentials authCredentials,
            @PathVariable(name = "ideaId") Long ideaId
    );
}