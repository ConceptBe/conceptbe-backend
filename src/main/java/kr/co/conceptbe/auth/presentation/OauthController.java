package kr.co.conceptbe.auth.presentation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import kr.co.conceptbe.auth.application.OauthService;
import kr.co.conceptbe.auth.application.dto.AuthResponse;
import kr.co.conceptbe.auth.application.dto.FindSignUpResponse;
import kr.co.conceptbe.auth.application.dto.OauthMemberResponse;
import kr.co.conceptbe.auth.application.dto.SignUpRequest;
import kr.co.conceptbe.auth.presentation.doc.AuthApi;
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
public class OauthController implements AuthApi {

    private final OauthService oauthService;

    @GetMapping("/oauth/{oauthServerType}")
    public ResponseEntity<Void> redirectAuthCodeRequestUrl(
        @PathVariable OauthServerType oauthServerType,
        HttpServletResponse response
    ) throws IOException {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/oauth/{oauthServerType}/member")
    public ResponseEntity<OauthMemberResponse> getOauthMemberInformation(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("code") String code
    ) {
        OauthMemberResponse oauthMemberResponse = oauthService.getOauthMemberInformationBy(oauthServerType, code);
        return ResponseEntity.ok(oauthMemberResponse);
    }

    @GetMapping("/oauth/{oauthServerType}/login")
    public ResponseEntity<AuthResponse> login(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("oauthId") String oauthId
    ) {
        AuthResponse authResponse = oauthService.login(oauthId, oauthServerType);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        AuthResponse authResponse = oauthService.signUp(signUpRequest);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/sign-up")
    public ResponseEntity<FindSignUpResponse> getSignUpInformation() {
        FindSignUpResponse signUpInFormation = oauthService.getSignUpInFormation();
        return ResponseEntity.ok(signUpInFormation);
    }
}
