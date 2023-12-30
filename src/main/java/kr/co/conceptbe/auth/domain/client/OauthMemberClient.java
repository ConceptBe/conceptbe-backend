package kr.co.conceptbe.auth.domain.client;

import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;
import kr.co.conceptbe.member.OauthServerType;

public interface OauthMemberClient {

    OauthServerType getServerType();

    OauthMemberInformation getOauthMemberInformation(String code);
}
