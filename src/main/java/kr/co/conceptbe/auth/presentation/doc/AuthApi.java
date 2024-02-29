package kr.co.conceptbe.auth.presentation.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import kr.co.conceptbe.auth.application.dto.AuthResponse;
import kr.co.conceptbe.auth.application.dto.FindSignUpResponse;
import kr.co.conceptbe.auth.application.dto.OauthMemberResponse;
import kr.co.conceptbe.auth.application.dto.SignUpRequest;
import kr.co.conceptbe.member.domain.OauthServerType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth", description = "인증 API")
public interface AuthApi {

    @Operation(summary = "인증코드 요청 URL 리다이렉트")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
        @PathVariable OauthServerType oauthServerType,
        HttpServletResponse response
    ) throws IOException;

    @Operation(summary = "Oauth 서버로부터 회원 정보 가져오기")
    ResponseEntity<OauthMemberResponse> getOauthMemberInformation(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("code") String code
    );

    @Operation(summary = "Oauth 로그인")
    ResponseEntity<AuthResponse> login(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("oauthId") String oauthId
    );

    @Operation(summary = "회원가입")
    ResponseEntity<AuthResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest);

    @Operation(summary = "회원가입 정보 조회")
    ResponseEntity<FindSignUpResponse> getSignUpInformation();
}
