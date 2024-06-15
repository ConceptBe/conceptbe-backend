package kr.co.conceptbe.image.domain.vo;

import static java.util.UUID.randomUUID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImageName {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
        "yyyyMMddHHmmssSSSSSS"
    );

    private final String fileName;

    private ImageName(String fileName) {
        this.fileName = fileName;
    }

    public static ImageName from(String originalFileName) {
        String fileName = createFileName();
        String extension = extractExtension(originalFileName);

        return new ImageName(fileName + extension);
    }

    private static String createFileName() {
        return FORMATTER.format(LocalDateTime.now())
            .concat("-")
            .concat(randomUUID().toString().replace("-", ""));
    }

    private static String extractExtension(String originalFileName) {
        return ImageExtension.from(originalFileName)
            .getExtension();
    }

    public String getFileName() {
        return fileName;
    }

}
