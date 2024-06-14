package kr.co.conceptbe.image.domain;

import java.util.List;
import kr.co.conceptbe.image.exception.ExceedImageSizeException;
import kr.co.conceptbe.image.exception.ImagesEmptyException;
import org.springframework.stereotype.Component;

@Component
public class ImageChecker {

    public static final int ADDITION_IMAGE_SIZE_LOWER_BOUND_INCLUSIVE = 1;
    public static final int IMAGE_SIZE_UPPER_BOUND_INCLUSIVE = 3;

    public void validateAdditionImagesSize(int additionImagesSize) {
        if (ADDITION_IMAGE_SIZE_LOWER_BOUND_INCLUSIVE <= additionImagesSize
            && additionImagesSize <= IMAGE_SIZE_UPPER_BOUND_INCLUSIVE) {
            return;
        }
        throw new ImagesEmptyException();
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

        if (imageSizeAfterUpdate <= IMAGE_SIZE_UPPER_BOUND_INCLUSIVE) {
            return;
        }

        throw new ExceedImageSizeException(IMAGE_SIZE_UPPER_BOUND_INCLUSIVE);
    }

}
