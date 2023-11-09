package com.b2.supercoding_prj01.web.controller;

import com.b2.supercoding_prj01.config.provider.JwtTokenProvider;
import com.b2.supercoding_prj01.repository.user.UserEntity;
import com.b2.supercoding_prj01.service.UserService;
import com.b2.supercoding_prj01.web.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> userSignup(@RequestBody UserDto user) {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserDto user, HttpServletResponse httpServletResponse) {
        return userService.userLogin(user, httpServletResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> userLogout(@RequestBody UserDto user, HttpServletRequest request) {
        return userService.userLogout(user, request);
    }


}
