package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.*;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import kr.co.conceptbe.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class IdeaLikeID implements Serializable {
	private Long memberId;
	private Long ideaId;

	private IdeaLikeID(Long memberId, Long ideaId) {
		this.memberId = memberId;
		this.ideaId = ideaId;
	}

	public static IdeaLikeID of(Member member, Idea idea) {
		return new IdeaLikeID(member.getId(), idea.getId());
	}
}
