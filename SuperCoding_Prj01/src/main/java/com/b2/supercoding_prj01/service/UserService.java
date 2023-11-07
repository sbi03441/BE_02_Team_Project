package com.b2.supercoding_prj01.service;

import com.b2.supercoding_prj01.dto.UserDto;
import com.b2.supercoding_prj01.entity.UserEntity;
import com.b2.supercoding_prj01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean signUp(UserDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();


        //1. 중복 이메일 가입 확인
        if( userRepository.existsByEmail(email)){
            return false;
        }
        //2 회원 저장
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);

        return true;
    }
}
