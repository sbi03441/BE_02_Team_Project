package com.b2.supercoding_prj01.service;



import com.b2.supercoding_prj01.dto.Login;
import com.b2.supercoding_prj01.dto.UserRequestDto;
import com.b2.supercoding_prj01.entity.UserEntity;
import com.b2.supercoding_prj01.exception.NotFoundException;
import com.b2.supercoding_prj01.jwt.JwtTokenProvider;
import com.b2.supercoding_prj01.repository.UserRepository;
import com.b2.supercoding_prj01.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public Long signUp(UserRequestDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();
        //1. 중복 이메일 가입 확인
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 가입된 메일 입니다");
        }
        //2 회원 저장
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                //    .role(UserEntity.builder().role(Role.USER).build().getRole())
                .build();

        userRepository.save(user);

        return user.getUserId();
    }

    @Transactional
    public String login(UserRequestDto loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("회원이 없습니다"));

            return jwtTokenProvider.createToken(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }


}
