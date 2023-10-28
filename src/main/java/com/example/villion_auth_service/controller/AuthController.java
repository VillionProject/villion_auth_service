package com.example.villion_auth_service.controller;

import com.example.villion_auth_service.config.TokenInfo;
import com.example.villion_auth_service.domain.reqeust.MemberRequest;
import com.example.villion_auth_service.domain.response.LoginResponse;
import com.example.villion_auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


// TODO auth 테스트 필요
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody MemberRequest request){
        return authService.login(request);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody MemberRequest request){
        authService.signUp(request);
    }

    @GetMapping("/me")
    public TokenInfo me(@AuthenticationPrincipal TokenInfo tokenInfo){
        return tokenInfo;
    }

}
