package com.b2.supercoding_prj01.web.controller;

import com.b2.supercoding_prj01.entity.UserEntity;
import com.b2.supercoding_prj01.repository.UserRepository;
import com.b2.supercoding_prj01.service.JwtService;
import com.b2.supercoding_prj01.service.UserService;
import com.b2.supercoding_prj01.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

//    회원가입 - signup
    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody UserDto user){
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
    public ResponseEntity<String> authenticateUser(@RequestBody UserDto user) {
            if(userService.checkUser(user)){
                String token = jwtService.createToken(userRepository.findUserIdByEmail(user.getEmail())); // 토큰 생성
                HttpHeaders headers = jwtService.addTokenToHeaders(token); // HttpHeaders 에 도큰 추가
                return ResponseEntity.status(200).headers(headers).body(user.getEmail() + "님 반갑습니다.");
            }
            else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("존재 하지 않는 user입니다.");
    }



}
