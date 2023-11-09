package com.b2.supercoding_prj01.service;

import com.b2.supercoding_prj01.config.provider.JwtTokenProvider;
import com.b2.supercoding_prj01.repository.user.UserEntity;
import com.b2.supercoding_prj01.repository.user.UserRepository;
import com.b2.supercoding_prj01.web.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원 가입
    public ResponseEntity<String> signup(UserDto user) {
        // 중복 가입 방지
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("이미 가입된 이메일입니다.");
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .password(encryptedPassword)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    // 로그인
    public ResponseEntity<String> userLogin(UserDto user, HttpServletResponse httpServletResponse) {
        UserEntity userEntity = userRepository.findByEmail(user.getEmail());
        if (userEntity != null && passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            // 로그인이 성공한 경우 JWT 토큰 발행
            String token = jwtTokenProvider.createToken(user.getEmail());
            httpServletResponse.setHeader("TOKEN",token);
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.badRequest().body("로그인 정보가 올바르지 않습니다.");
        }
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 로그아웃
    public ResponseEntity<Map<String, String>> userLogout(UserDto user, HttpServletRequest request) {
        String email = user.getEmail();
        String token = jwtTokenProvider.resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            Claims claims = Jwts.parser().setSigningKey(jwtTokenProvider.getSecretKey()).parseClaimsJws(token).getBody();
            String tokenEmail = claims.getSubject();

            if (email.equals(tokenEmail)) {
                jwtTokenProvider.invalidateToken(token); // 토큰 무효화

                return ResponseEntity.ok(Map.of("message", "로그아웃되었습니다."));
            }
        }

        return ResponseEntity.badRequest().body(Map.of("message", "로그아웃 실패"));
    }


}
