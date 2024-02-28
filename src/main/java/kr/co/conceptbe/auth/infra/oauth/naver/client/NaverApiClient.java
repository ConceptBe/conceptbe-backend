package kr.co.conceptbe.auth.infra.oauth.naver.client;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import kr.co.conceptbe.auth.infra.oauth.naver.dto.NaverMemberResponse;
import kr.co.conceptbe.auth.infra.oauth.naver.dto.NaverResponseToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface NaverApiClient {

    @PostExchange(url = "https://nid.naver.com/oauth2.0/token", contentType = APPLICATION_FORM_URLENCODED_VALUE)
    NaverResponseToken requestToken(@RequestParam MultiValueMap<String, String> params);

    @GetExchange("https://openapi.naver.com/v1/nid/me")
    NaverMemberResponse requestMemberInformation(@RequestHeader(name = AUTHORIZATION) String accessToken);
}
