package com.b2.supercoding_prj01.service;

import com.b2.supercoding_prj01.dto.UserDto;
import com.b2.supercoding_prj01.entity.UserEntity;
import com.b2.supercoding_prj01.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> saveUser(UserDto userDto) {


    }
}
