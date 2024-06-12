package kr.co.conceptbe.image.application.response;

import kr.co.conceptbe.image.domain.Image;
import org.springframework.beans.factory.annotation.Value;

public record ImageResponse(
    Long id,
    Long ideaId,
    String imageUrl
) {

    @Value("${cloud-front-url}")
    private static String cloudFrontUrl;

    public static ImageResponse from(Image image) {
        return new ImageResponse(
            image.getId(),
            image.getIdeaId(),
            String.join("/", cloudFrontUrl, image.getImageUrl())
        );
    }
}
