package kr.co.conceptbe.idea.domain.event;

import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;

public record CreatedIdeaEvent(
        Idea idea,
        Member member
) {
}
