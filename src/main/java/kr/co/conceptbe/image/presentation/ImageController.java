package kr.co.conceptbe.image.presentation;

import java.util.List;
import kr.co.conceptbe.image.application.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/{ideaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> saveImage(
        @PathVariable Long ideaId,
        @RequestPart List<MultipartFile> file
    ) {
        Long savedImageId = imageService.save(ideaId, file);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(savedImageId);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.delete(imageId);
        return ResponseEntity.noContent().build();
    }

}
