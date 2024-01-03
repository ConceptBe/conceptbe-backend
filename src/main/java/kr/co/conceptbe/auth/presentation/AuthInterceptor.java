package kr.co.conceptbe.auth.presentation;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.conceptbe.auth.support.BearerTokenExtractor;
import kr.co.conceptbe.auth.support.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        if (CorsUtils.isPreFlightRequest(request) || hasNotAuthorization(request)) {
            return true;
        }
        String accessToken = BearerTokenExtractor.extract(request);
        jwtProvider.validateParseJws(accessToken);
        return true;
    }

    private boolean hasNotAuthorization(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION) == null;
    }
}
