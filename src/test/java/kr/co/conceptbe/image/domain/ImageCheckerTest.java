package kr.co.conceptbe.image.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Stream;
import kr.co.conceptbe.image.exception.ExceedImageSizeException;
import kr.co.conceptbe.image.exception.ImagesEmptyException;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ImageCheckerTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void 추가하려는_이미지_개수가_정상적인_것을_확인한다(int additionImagesSize) {
        //given
        ImageChecker imageChecker = new ImageChecker();

        //when
        Executable executable = () -> imageChecker.validateAdditionImagesSize(additionImagesSize);

        //then
        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 4})
    void 추가하려는_이미지_개수가_비정상적인_것을_확인한다(int additionImagesSize) {
        //given
        ImageChecker imageChecker = new ImageChecker();

        //when
        ThrowingCallable throwingCallable =
            () -> imageChecker.validateAdditionImagesSize(additionImagesSize);

        //then
        assertThatThrownBy(throwingCallable)
            .isInstanceOfAny(ImagesEmptyException.class, ExceedImageSizeException.class);
    }

    @Test
    void 삭제해야_하는_이미지의_아이디를_반환하는_것을_확인한다() {
        //given
        List<Long> originalImageIds = List.of(1L, 2L, 3L);
        List<Long> changedImageIds = List.of(1L, 2L);
        ImageChecker imageChecker = new ImageChecker();

        //when
        List<Long> imageIdsToDeleted = imageChecker.getImageIdsToDeleted(
            originalImageIds,
            changedImageIds
        );

        //then
        assertThat(imageIdsToDeleted).containsExactly(3L);
    }

    @ParameterizedTest
    @MethodSource("validImageUpdate")
    void 이미지_업데이트시_이미지_개수가_초과되지_않는_것을_확인한다(
        int originalImageSize,
        int deletedImageSize,
        int additionImageSize
    ) {
        //given
        ImageChecker imageChecker = new ImageChecker();

        //when
        Executable executable = () -> imageChecker.validateTotalImageSize(
            originalImageSize,
            deletedImageSize,
            additionImageSize
        );

        //then
        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @MethodSource("invalidImageUpdate")
    void 이미지_업데이트시_이미지_개수가_초과되는_것을_확인한다(
        int originalImageSize,
        int deletedImageSize,
        int additionImageSize
    ) {
        //given
        ImageChecker imageChecker = new ImageChecker();

        //when
        ThrowingCallable throwingCallable = () -> imageChecker.validateTotalImageSize(
            originalImageSize,
            deletedImageSize,
            additionImageSize
        );

        //then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(ExceedImageSizeException.class);
    }

    static Stream<Arguments> validImageUpdate() {
        return Stream.of(
            Arguments.of(3, 2, 2),
            Arguments.of(3, 2, 1),
            Arguments.of(3, 3, 1),
            Arguments.of(3, 3, 0)
        );
    }

    static Stream<Arguments> invalidImageUpdate() {
        return Stream.of(
            Arguments.of(3, 2, 3),
            Arguments.of(3, 3, 4),
            Arguments.of(3, 0, 1)
        );
    }

}
