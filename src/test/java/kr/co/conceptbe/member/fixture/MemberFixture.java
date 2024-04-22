package kr.co.conceptbe.member.fixture;

import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.member.domain.vo.Nickname;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.skill.domain.SkillCategory;

public class MemberFixture {

    public static Member createMember(Region region) {
        return new Member(
            new OauthId("1", OauthServerType.KAKAO),
            Nickname.from("nickname"),
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            region
        );
    }

    public static Member createMemberByMainSkill(SkillCategory mainSkill, Region region) {
        Member member = new Member(
            new OauthId("1", OauthServerType.KAKAO),
            Nickname.from("nickname"),
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            region
        );
        member.updateMainSkill(mainSkill);
        return member;
    }

    public static Member createMemberByOauthId(OauthId oauthId, Region region) {
        return new Member(
            oauthId,
            Nickname.from("nickname"),
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            region
        );

    }

}
