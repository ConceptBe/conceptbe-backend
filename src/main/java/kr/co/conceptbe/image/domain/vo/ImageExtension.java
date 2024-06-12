package kr.co.conceptbe.image.domain.vo;

import java.util.Arrays;
import kr.co.conceptbe.image.exception.BadImageExtensionException;

public enum ImageExtension {

    JPEG(".jpeg"),
    JPG(".jpg"),
    JFIF(".jfif"),
    PNG(".png"),
    SVG(".svg"),
    ;

    private final String extension;

    ImageExtension(final String extension) {
        this.extension = extension;
    }

    public static ImageExtension from(String imageFileName) {
        return Arrays.stream(values())
            .filter(imageExtension -> imageFileName.endsWith(imageExtension.getExtension()))
            .findFirst()
            .orElseThrow(BadImageExtensionException::new);
    }

    public String getExtension() {
        return extension;
    }

}
