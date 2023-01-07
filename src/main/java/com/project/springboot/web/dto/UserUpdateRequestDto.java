package com.project.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private String address;

    @Builder
    public UserUpdateRequestDto( String password, String nickname,String phone,
                                 String email, String address){
        this.password=password;
        this.nickname=nickname;
        this.phone=phone;
        this.email=email;
        this.address=address;
    }
}

