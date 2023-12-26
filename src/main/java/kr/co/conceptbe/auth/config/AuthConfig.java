package kr.co.conceptbe.auth.config;

import kr.co.conceptbe.auth.presentation.OauthServerTypeConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class AuthConfig implements WebMvcConfigurer {

    //TODO cors 설정

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OauthServerTypeConverter());
    }
}
