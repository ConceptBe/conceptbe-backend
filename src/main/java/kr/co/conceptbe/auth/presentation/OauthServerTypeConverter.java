package kr.co.conceptbe.auth.presentation;

import kr.co.conceptbe.member.domain.OauthServerType;
import org.springframework.core.convert.converter.Converter;

public class OauthServerTypeConverter implements Converter<String, OauthServerType> {

    @Override
    public OauthServerType convert(String source) {
        return OauthServerType.from(source);
    }
}
