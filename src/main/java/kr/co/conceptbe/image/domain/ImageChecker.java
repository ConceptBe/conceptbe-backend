package kr.co.conceptbe.image.domain;

import java.util.List;
import kr.co.conceptbe.image.exception.InvalidImagesSizeException;
import org.springframework.stereotype.Component;

@Component
public class ImageChecker {

    private static final int IMAGE_SIZE_LOWER_BOUND_INCLUSIVE = 0;
    private static final int IMAGE_SIZE_UPPER_BOUND_INCLUSIVE = 3;

    public void validateImagesSize(int additionImagesSize) {
        if (IMAGE_SIZE_LOWER_BOUND_INCLUSIVE <= additionImagesSize
            && additionImagesSize <= IMAGE_SIZE_UPPER_BOUND_INCLUSIVE) {
            return;
        }
        throw new InvalidImagesSizeException(IMAGE_SIZE_UPPER_BOUND_INCLUSIVE);
    }

    public List<Long> getImageIdsToDeleted(
        List<Long> originalImageIds,
        List<Long> changedImageIds
    ) {
        return originalImageIds.stream()
            .filter(id -> !changedImageIds.contains(id))
            .toList();
    }

    public void validateTotalImageSize(
        int originalImageSize,
        int deletedImageSize,
        int additionImageSize
    ) {
        int imageSizeAfterDeleted = originalImageSize - deletedImageSize;
        int imageSizeAfterUpdate = imageSizeAfterDeleted + additionImageSize;
        validateImagesSize(imageSizeAfterUpdate);
    }

}
