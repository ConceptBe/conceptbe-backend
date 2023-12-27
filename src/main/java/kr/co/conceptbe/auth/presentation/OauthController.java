package kr.co.conceptbe.auth.presentation;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kr.co.conceptbe.auth.application.dto.OauthMemberResponse;
import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.member.OauthServerType;
import kr.co.conceptbe.auth.application.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
        @PathVariable OauthServerType oauthServerType,
        HttpServletResponse response
    ) throws IOException {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{oauthServerType}/member")
    ResponseEntity<OauthMemberResponse> getOauthMemberInformation(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("code") String code
    ) {
        OauthMemberResponse oauthMemberResponse = oauthService.getOauthMemberInformationBy(oauthServerType, code);
        return ResponseEntity.ok(oauthMemberResponse);
    }

    @GetMapping("/{oauthServerType}/login")
    ResponseEntity<TokenResponse> login(
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("oauthId") String oauthId
    ) {
        String accessToken = oauthService.login(oauthId, oauthServerType);
        return ResponseEntity.ok(TokenResponse.from(accessToken));
    }
}
