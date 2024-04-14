package kr.co.conceptbe.member.fixture;

import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.member.domain.vo.Nickname;
import kr.co.conceptbe.skill.domain.SkillCategory;

public class MemberFixture {

    public static Member createMember() {
        return new Member(
            new OauthId("1", OauthServerType.KAKAO),
            Nickname.from("nickname"),
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            kr.co.conceptbe.member.domain.Region.BUSAN
        );
    }

    public static Member createMemberByMainSkill(SkillCategory mainSkill) {
        Member member = new Member(
            new OauthId("1", OauthServerType.KAKAO),
            Nickname.from("nickname"),
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            kr.co.conceptbe.member.domain.Region.BUSAN
        );
        member.updateMainSkill(mainSkill);
        return member;
    }

    public static Member createMemberByOauthId(OauthId oauthId) {
        return new Member(
            oauthId,
            Nickname.from("nickname"),
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            kr.co.conceptbe.member.domain.Region.BUSAN
        );

    }

}
