package kr.co.conceptbe.bookmark;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookmarkID implements Serializable {
	private Long memberId;
	private Long ideaId;
}
