package com.project.springboot.web.dto;

import com.project.springboot.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDetailDto {
    private Long uid;
    private String userid;
    private String password;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String address;

    public UserDetailDto(User entity){
        this.uid=entity.getUid();
        this.userid=entity.getUserid();
        this.password= entity.getPassword();
        this.username=entity.getUsername();
        this.nickname= entity.getNickname();;
        this.phone=entity.getPhone();
        this.email=entity.getEmail();
        this.address=entity.getAddress();
    }


}
