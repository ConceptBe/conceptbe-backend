package kr.co.conceptbe.image.presentation;

import java.util.List;
import kr.co.conceptbe.image.application.ImageService;
import kr.co.conceptbe.image.presentation.doc.ImageApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController implements ImageApi {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/{ideaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveImage(
        @PathVariable Long ideaId,
        @RequestPart List<MultipartFile> files
    ) {
        imageService.save(ideaId, files);
        return ResponseEntity.status(HttpStatus.CREATED)
            .build();
    }

    @PatchMapping("/{ideaId}")
    public ResponseEntity<Void> updateImages(
        @PathVariable Long ideaId,
        @RequestParam("image-ids") List<Long> imageIds,
        @RequestPart List<MultipartFile> additionFiles
    ) {
        imageService.update(ideaId, imageIds, additionFiles);
        return ResponseEntity.noContent().build();
    }

}
