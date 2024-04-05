package kr.co.conceptbe.bookmark;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkID implements Serializable {
	private Long memberId;
	private Long ideaId;

	private BookmarkID(Long memberId, Long ideaId) {
		this.memberId = memberId;
		this.ideaId = ideaId;
	}

	public static BookmarkID of(Long memberId, Long ideaId) {
		return new BookmarkID(memberId, ideaId);
	}
}
