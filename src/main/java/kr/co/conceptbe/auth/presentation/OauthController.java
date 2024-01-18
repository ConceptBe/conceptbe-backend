package kr.co.conceptbe.auth.presentation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import kr.co.conceptbe.auth.application.OauthService;
import kr.co.conceptbe.auth.application.dto.OauthMemberResponse;
import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.auth.application.dto.FindSignUpResponse;
import kr.co.conceptbe.auth.application.dto.SignUpRequest;
import kr.co.conceptbe.member.domain.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/oauth/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
        @PathVariable OauthServerType oauthServerType,
        HttpServletResponse response
    ) throws IOException {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/oauth/{oauthServerType}/member")
    ResponseEntity<OauthMemberResponse> getOauthMemberInformation(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("code") String code
    ) {
        OauthMemberResponse oauthMemberResponse = oauthService.getOauthMemberInformationBy(oauthServerType, code);
        return ResponseEntity.ok(oauthMemberResponse);
    }

    @GetMapping("/oauth/{oauthServerType}/login")
    ResponseEntity<TokenResponse> login(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("oauthId") String oauthId
    ) {
        String accessToken = oauthService.login(oauthId, oauthServerType);
        return ResponseEntity.ok(new TokenResponse(accessToken));
    }

    @PostMapping("/sign-up")
    ResponseEntity<TokenResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        TokenResponse tokenResponse = oauthService.signUp(signUpRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/sign-up")
    ResponseEntity<FindSignUpResponse> getSignUpInformation() {
        FindSignUpResponse signUpInFormation = oauthService.getSignUpInFormation();
        return ResponseEntity.ok(signUpInFormation);
    }
}
