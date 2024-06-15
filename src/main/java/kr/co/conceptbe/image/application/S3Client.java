package kr.co.conceptbe.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import kr.co.conceptbe.image.domain.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Client {

    @Value("${s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    public String upload(MultipartFile multipartFile) {
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

    public void delete(String url) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, url));
    }

}
