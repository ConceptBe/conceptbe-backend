package kr.co.conceptbe.image.presentation;

import kr.co.conceptbe.image.application.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/{ideaId}")
    public ResponseEntity<Long> saveImage(@PathVariable Long ideaId, MultipartFile file) {
        Long savedImageId = imageService.save(ideaId, file);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(savedImageId);
    }

}
