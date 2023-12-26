package kr.co.conceptbe.auth.domain.authcode;

import kr.co.conceptbe.member.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType getServerType();

    String getUrl();
}
