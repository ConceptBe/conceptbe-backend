package kr.co.conceptbe.auth.support;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.crypto.SecretKey;
import kr.co.conceptbe.auth.config.JwtCredentials;
import kr.co.conceptbe.auth.exception.TokenExpiredException;
import kr.co.conceptbe.auth.exception.TokenInvalidException;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final SecretKey key;
    private final long accessTokenExpirationTime;
    //TODO refresh 추가

    public JwtProvider(JwtCredentials jwtCredentials) {
        this.key = hmacShaKeyFor(jwtCredentials.getSecretKey().getBytes(UTF_8));
        this.accessTokenExpirationTime = jwtCredentials.getAccessTokenExpirationTime();
    }

    public String createAccessToken(Long memberId) {
        return createToken(memberId.toString(), accessTokenExpirationTime, key);
    }

    private String createToken(String payload, long expireLength, SecretKey key) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expireLength);
        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getPayload(String token) {
        return validateParseJws(token).getBody().getSubject();
    }

    public Jws<Claims> validateParseJws(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException();
        } catch (JwtException e) {
            throw new TokenInvalidException();
        }
    }
}
