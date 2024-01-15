package kr.co.conceptbe.auth.domain.authcode;

import kr.co.conceptbe.member.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType getServerType();

    String getUrl();
}
