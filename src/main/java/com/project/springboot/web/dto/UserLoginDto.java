package com.project.springboot.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {
    private Long uid;
    private String userid;
    private String password;

    private String nickname;

    @Builder
    public UserLoginDto( Long uid,String userid, String password,
                         String nickname){
        this.uid=uid;
        this.userid=userid;
        this.password=password;
        this.nickname=nickname;

    }
}
