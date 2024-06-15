package kr.co.conceptbe.image.application.response;

import kr.co.conceptbe.image.domain.Image;

public record ImageResponse(
    Long id,
    Long ideaId,
    String imageUrl
) {

    public static ImageResponse of(Image image, String cloudFrontUrl) {
        return new ImageResponse(
            image.getId(),
            image.getIdeaId(),
            String.join("/", cloudFrontUrl, image.getImageUrl())
        );
    }
}
