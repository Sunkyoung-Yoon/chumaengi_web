package com.project.springboot.web.dto;

import com.project.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
public class UserSaveRequestDto {
    @NotBlank(message = "아이디를 작성해주세요.")
    @Pattern(regexp= "[a-zA-Z0-9]{4,12}", message = "영어 대소문자와 숫자로 4자~12자내로 작성해주세요.")
    private String userid;
    private String password;
    @NotBlank(message = "이름을 작성해주세요.")
    private String username;
    @NotBlank(message = "닉네임을 작성해주세요.")
    private String nickname;
    @NotBlank(message = "연락처를 작성해주세요.")
    private String phone;
    @NotBlank(message = "이메일을 작성해주세요.")
    private String email;
    @NotBlank(message = "주소를 작성해주세요.")
    private String address;


    @Builder //해당 클래스의 빌더패턴클래스 생성, 생성자 상단에 선언시 생성자레 포함된 필드만 빌더에 포함
    public UserSaveRequestDto(String userid, String password, String username, String nickname, String phone,
                String email, String address){
        this.userid=userid;
        this.password=password;
        this.username=username;
        this.nickname=nickname;
        this.phone=phone;
        this.email=email;
        this.address=address;
    }


    public User toEntity(){
        return User.builder()
                .userid(userid)
                .password(password)
                .username(username)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .address(address)
                .build();
    }
}
