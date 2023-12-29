package kr.co.conceptbe.auth.domain.client;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;
import kr.co.conceptbe.member.OauthServerType;
import kr.co.conceptbe.member.exception.NotFoundOauthServerTypeException;
import org.springframework.stereotype.Component;

@Component
public class OauthMemberClientHandler {

    private final Map<OauthServerType, OauthMemberClient> mapping;

    public OauthMemberClientHandler(Set<OauthMemberClient> clients) {
        mapping = clients.stream()
                .collect(toMap(
                        OauthMemberClient::getServerType,
                        identity()
                ));
    }

    public OauthMemberInformation getOauthMemberInformation(OauthServerType oauthServerType, String authCode) {
        return getClient(oauthServerType).getOauthMemberInformation(authCode);
    }

    private OauthMemberClient getClient(OauthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType)).orElseThrow(NotFoundOauthServerTypeException::new);
    }
}
