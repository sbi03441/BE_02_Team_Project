package com.b2.supercoding_prj01.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("mySecretKey")
    private String mySecretKey; // 비밀 키

    //토큰 생성
    public String createToken(Optional<Long> user_idx) {
        return Jwts.builder()
                .setSubject(String.valueOf(user_idx))
                .signWith(SignatureAlgorithm.HS256, mySecretKey)
//                .setIssuedAt()
//                .setExpiration()
                .compact();
    }

    // 생성한 코드 로그인 시 HttpHeaders에 넘겨줌
    public HttpHeaders addTokenToHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return headers;
    }

}
