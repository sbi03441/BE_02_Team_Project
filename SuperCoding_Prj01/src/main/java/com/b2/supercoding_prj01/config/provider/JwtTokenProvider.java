package com.b2.supercoding_prj01.config.provider;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey; //
    private final long validityInMilliseconds = 3600000; // 토큰의 유효시간 (1시간)

    private final Set<String> invalidTokens = new HashSet<>(); // 토큰 유효성 검증

    //토큰 가져오기
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("TOKEN");
    }


    // 토큰 생성
    public String createToken(String userEmail) {
        Claims claims = Jwts.claims().setSubject(userEmail);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //시크릿 키 가져오기
    public String getSecretKey() {
        return secretKey;
    }

    // 만료 토큰 넣기
    public void invalidateToken(String token) {
        invalidTokens.add(token);
    }

    // 만료 토큰 포함 여부
//    public boolean isTokenInvalid(String token) {
//        return invalidTokens.contains(token);
//    }


}
