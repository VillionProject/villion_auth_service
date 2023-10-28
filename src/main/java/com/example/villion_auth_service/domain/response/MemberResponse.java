package com.example.villion_auth_service.domain.response;

import com.project.villion.domain.entity.Member;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MemberResponse {
    private String loginId;
    private String password;
    private String libraryName;
    private String email;
    private String location;
    private boolean libraryStatus; // 도서관 상태(운영/휴관)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate signInDate;

//    Member에 저장된 값 가져와서 응답하기
    public MemberResponse(Member member) {
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.libraryName = member.getLibraryName();
        this.email = member.getEmail();
        this.location = member.getLocation();
        this.libraryStatus = member.isLibraryStatus();
        this.signInDate = member.getSignInDate();
    }

}
