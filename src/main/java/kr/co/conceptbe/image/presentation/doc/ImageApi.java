package kr.co.conceptbe.image.presentation.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Image", description = "이미지 API")
public interface ImageApi {

    @Operation(summary = "게시글 관련 이미지 업로드")
    ResponseEntity<Void> saveImage(
        @PathVariable Long ideaId,
        @RequestPart List<MultipartFile> files
    );

    @Operation(summary = "게시글 관련 이미지 수정")
    ResponseEntity<Void> updateImages(
        @PathVariable Long ideaId,
        @RequestParam("image-ids") List<Long> imageIds,
        @RequestPart List<MultipartFile> additionFiles
    );

}
