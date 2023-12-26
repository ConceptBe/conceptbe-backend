package kr.co.conceptbe.auth.application;

import kr.co.conceptbe.auth.domain.authcode.AuthCodeRequestUrlProviderHandler;
import kr.co.conceptbe.member.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderHandler authCodeRequestUrlProviderHandler;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderHandler.provideUrl(oauthServerType);
    }
}
