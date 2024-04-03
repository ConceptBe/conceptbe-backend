package kr.co.conceptbe.auth.config;

import java.util.List;
import kr.co.conceptbe.auth.presentation.AuthInterceptor;
import kr.co.conceptbe.auth.presentation.MandatoryJwtArgumentResolver;
import kr.co.conceptbe.auth.presentation.OauthServerTypeConverter;
import kr.co.conceptbe.auth.presentation.OptionalJwtArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final MandatoryJwtArgumentResolver mandatoryJwtArgumentResolver;
    private final OptionalJwtArgumentResolver optionalJwtArgumentResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "http://10.0.2.2:3000", "http://conceptbe.kr")
            .allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.PATCH.name()
            )
            .allowCredentials(true)
            .exposedHeaders("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OauthServerTypeConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(mandatoryJwtArgumentResolver);
        resolvers.add(optionalJwtArgumentResolver);
    }
}
