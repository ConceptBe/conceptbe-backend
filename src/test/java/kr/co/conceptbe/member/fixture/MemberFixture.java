package kr.co.conceptbe.member.fixture;

import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;

public class MemberFixture {

    public static Member createMember() {
        return new Member(
            new OauthId("1", OauthServerType.KAKAO),
            "nickname",
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            kr.co.conceptbe.member.domain.Region.BUSAN
        );
    }

}
