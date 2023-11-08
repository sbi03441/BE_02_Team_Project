package com.b2.supercoding_prj01.web.controller;

import com.b2.supercoding_prj01.dto.UserRequestDto;

import com.b2.supercoding_prj01.repository.UserRepository;
import com.b2.supercoding_prj01.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//    회원가입 - signup
    @PostMapping("/signup")
    public String register(@RequestBody UserRequestDto userDto) {
        return userService.signUp(userDto);
    }


    @PostMapping(value = "/login")
    public String login(@RequestBody UserRequestDto loginRequest, HttpServletResponse httpServletResponse){
        String token = userService.login(loginRequest);
        httpServletResponse.setHeader("X-AUTH-TOKEN", token);
        return "로그인이 성공하였습니다.";
    }

}
