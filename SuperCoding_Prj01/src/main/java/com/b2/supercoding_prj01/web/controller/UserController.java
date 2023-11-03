package com.b2.supercoding_prj01.web.controller;

import com.b2.supercoding_prj01.entity.UserEntity;
import com.b2.supercoding_prj01.repository.UserRepository;
import com.b2.supercoding_prj01.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
//    회원가입 - signup
    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody UserEntity user){
        return userService.saveUser(user);
    }

    @GetMapping("/get")
    public List<UserEntity> getuser(){
        List<UserEntity> userList = userRepository.findAll();
        if (userList.isEmpty())
            return null;
        else
            return userList;
    }



//    로그인 - login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // 사용자 인증 및 토큰 생성
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }




}
