package com.b2.supercoding_prj01.web.controller;

import com.b2.supercoding_prj01.dto.UserDto;
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

//    회원가입 - signup
    @PostMapping("/signup")
    public String register(@RequestBody UserDto userDto) {
        boolean isSuccess = userService.signUp(userDto);
        return isSuccess ? "회원 가입이 완료 되었습니다" : "회원가입에 실패하였습니다";
    }
//    @GetMapping("/get")
//    public List<UserEntity> getuser(){
//        List<UserEntity> userList = userRepository.findAll();
//        if (userList.isEmpty())
//            return null;
//        else
//            return userList;
//    }







}
