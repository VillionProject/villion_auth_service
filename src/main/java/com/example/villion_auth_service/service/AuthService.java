package com.example.villion_auth_service.service;

import com.example.villion_auth_service.config.JwtService;
import com.example.villion_auth_service.domain.entity.Member;
import com.example.villion_auth_service.domain.reqeust.MemberRequest;
import com.example.villion_auth_service.domain.response.LoginResponse;
import com.example.villion_auth_service.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void signUp(MemberRequest request) {
        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword())) // Security기능
                .libraryName(request.getLibraryName())
                .email(request.getEmail())
                .location(request.getLocation())
                .libraryStatus(request.isLibraryStatus())
                .build();
        memberRepository.save(member);
    }

    public LoginResponse login(MemberRequest request) {
        Optional<Member> byLoginId = memberRepository.findByLoginId(request.getLoginId());
        Member member = byLoginId.orElseThrow(() -> new IllegalArgumentException("MEMBER NOT FOUND"));
//        passwordEncoder.matches 메서드는 입력한 비밀번호와 해싱된 비밀번호(저장된 비밀번호(member.getPassword()))를 비교
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("MEMBER NOT FOUND");
        }
        String token = jwtService.makeToken(member);
        return new LoginResponse(token);

    }

}
