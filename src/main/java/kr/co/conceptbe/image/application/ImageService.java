package kr.co.conceptbe.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import kr.co.conceptbe.image.domain.IdeaValidator;
import kr.co.conceptbe.image.domain.Image;
import kr.co.conceptbe.image.domain.ImageRepository;
import kr.co.conceptbe.image.domain.UploadFile;
import kr.co.conceptbe.image.exception.IdeaNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Value("${s3.bucket}")
    private String bucket;
    //    @Value("${cloud-front-url}")
//    private String cloudFrontUrl;
    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;
    private final IdeaValidator ideaValidator;

    public ImageService(
        AmazonS3 amazonS3,
        ImageRepository imageRepository,
        IdeaValidator ideaValidator
    ) {
        this.amazonS3 = amazonS3;
        this.imageRepository = imageRepository;
        this.ideaValidator = ideaValidator;
    }

    public Long save(Long ideaId, MultipartFile multipartFile) {
        validateIdea(ideaId);
        String imageUrl = upload(multipartFile);
        Image image = new Image(ideaId, imageUrl);
        Image savedImage = imageRepository.save(image);
        return savedImage.getId();
    }

    private void validateIdea(Long ideaId) {
        if (ideaValidator.existsIdea(ideaId)) {
            return;
        }
        throw new IdeaNotFoundException();
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

//    private String getUploadPath(UploadFile uploadFile) {
//        return String.join(
//            "/",
//            cloudFrontUrl,
//            uploadFile.getOriginalFilename()
//        );
//    }


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

    public void delete(Long imageId) {
        Image image = imageRepository.getById(imageId);
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, image.getImageUrl()));
        imageRepository.delete(image);
    }

}
