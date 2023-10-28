package com.example.villion_auth_service.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TokenInfo implements UserDetails {
    private String loinId;
    private String email;
    private String libraryName;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null; // 사용자 비밀번호
    }

    @Override
    public String getUsername() {
        return null; // 사용자 이름
    }

    @Override
    public boolean isAccountNonExpired() {
        return false; // 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return false; // 계정 잠금 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false; // 자격 증명(Credentials) 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return false; // 계정 활성화 여부
    }
}
