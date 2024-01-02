package kr.co.conceptbe.idea;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class IdeaLikeID implements Serializable {
	private Long memberId;
	private Long ideaId;
}
