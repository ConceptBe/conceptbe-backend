package kr.co.conceptbe.image.presentation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.common.auth.Auth;
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
        @Auth AuthCredentials authCredentials,
        @PathVariable Long ideaId,
        @RequestPart List<MultipartFile> files
    ) {
        imageService.save(ideaId, authCredentials, files);
        return ResponseEntity.status(HttpStatus.CREATED)
            .build();
    }

    @PatchMapping(value = "/{ideaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImages(
        @Auth AuthCredentials authCredentials,
        @PathVariable Long ideaId,
        @RequestParam(value = "image-ids", required = false) List<Long> imageIds,
        @RequestPart(required = false) List<MultipartFile> additionFiles
    ) {
        imageService.update(
            ideaId,
            authCredentials,
            Optional.ofNullable(imageIds)
                .orElseGet(Collections::emptyList),
            Optional.ofNullable(additionFiles)
                .orElseGet(Collections::emptyList)
        );
        return ResponseEntity.ok().build();
    }

}
