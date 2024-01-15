package kr.co.conceptbe.auth.domain.authcode;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.member.exception.NotFoundOauthServerTypeException;
import org.springframework.stereotype.Component;

@Component
public class AuthCodeRequestUrlProviderHandler {

    private final Map<OauthServerType, AuthCodeRequestUrlProvider> mapping;

    public AuthCodeRequestUrlProviderHandler(Set<AuthCodeRequestUrlProvider> providers) {
        mapping = providers.stream()
                .collect(toMap(
                        AuthCodeRequestUrlProvider::getServerType,
                        identity()
                ));
    }

    public String provideUrl(OauthServerType oauthServerType) {
        return getProvider(oauthServerType).getUrl();
    }

    private AuthCodeRequestUrlProvider getProvider(OauthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType)).orElseThrow(NotFoundOauthServerTypeException::new);
    }
}
