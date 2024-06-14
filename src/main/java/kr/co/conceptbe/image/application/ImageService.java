package kr.co.conceptbe.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import kr.co.conceptbe.image.domain.IdeaValidator;
import kr.co.conceptbe.image.domain.Image;
import kr.co.conceptbe.image.domain.ImageChecker;
import kr.co.conceptbe.image.domain.ImageRepository;
import kr.co.conceptbe.image.domain.UploadFile;
import kr.co.conceptbe.image.exception.IdeaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    @Value("${s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;
    private final IdeaValidator ideaValidator;
    private final ImageChecker imageChecker;

    public void save(Long ideaId, List<MultipartFile> files) {
        validateIdea(ideaId);
        imageChecker.validateAdditionImagesSize(files.size());
        uploadImages(ideaId, files);
    }

    private void validateIdea(Long ideaId) {
        if (ideaValidator.existsIdea(ideaId)) {
            return;
        }
        throw new IdeaNotFoundException();
    }

    private void uploadImages(Long ideaId, List<MultipartFile> files) {
        files.stream()
            .map(this::upload)
            .map(imageUrl -> new Image(ideaId, imageUrl))
            .forEach(imageRepository::save);
    }

    private String upload(MultipartFile multipartFile) {
        try {
            UploadFile uploadFile = UploadFile.from(multipartFile);
            fileUpload(uploadFile);
            return uploadFile.getOriginalFilename();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void fileUpload(MultipartFile multipartFile) throws IOException {
        File tempFile = null;

        try {
            tempFile = File.createTempFile("upload_", ".tmp");
            multipartFile.transferTo(tempFile);
            amazonS3.putObject(new PutObjectRequest(
                bucket,
                multipartFile.getOriginalFilename(),
                tempFile
            ));
        } catch (IOException exception) {
            throw new IOException(exception);
        } finally {
            removeTempFileIfExists(tempFile);
        }
    }

    private void removeTempFileIfExists(File tempFile) {
        if (Objects.nonNull(tempFile) && tempFile.exists()) {
            tempFile.delete();
        }
    }

    public void update(Long ideaId, List<Long> imageIds, List<MultipartFile> additionFiles) {
        validateIdea(ideaId);
        List<Image> imagesToDeleted = getImagesToDeleted(ideaId, imageIds, additionFiles.size());
        imagesToDeleted.forEach(this::deleteImage);
        additionFiles.forEach(this::upload);
    }

    private List<Image> getImagesToDeleted(
        Long ideaId,
        List<Long> imageIds,
        int additionFilesSize
    ) {
        List<Image> savedImages = imageRepository.findAllByIdeaId(ideaId);
        List<Long> imageIdsToDeleted = imageChecker.getImageIdsToDeleted(
            extractIds(savedImages),
            imageIds
        );
        imageChecker.validateTotalImageSize(
            savedImages.size(),
            imageIdsToDeleted.size(),
            additionFilesSize
        );
        return savedImages.stream()
            .filter(image -> imageIdsToDeleted.contains(image.getId()))
            .toList();
    }

    private List<Long> extractIds(List<Image> savedImages) {
        return savedImages.stream()
            .map(Image::getId)
            .toList();
    }

    private void deleteImage(Image image) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, image.getImageUrl()));
        imageRepository.delete(image);
    }

}
