package com.project.springboot.web;

import com.project.springboot.service.user.UserService;
import com.project.springboot.web.dto.UserLoginDto;
import com.project.springboot.web.dto.UserSaveRequestDto;
import com.project.springboot.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.project.springboot.web.UserApiController.SessionConst.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    public interface SessionConst {
        String LOGIN_USERID = "userid";
    }


    //회원아이디중복확인 통과면 회원등록
    @PostMapping("/api/user/signup")
    public String checksave(@RequestBody UserSaveRequestDto requestDto ) {
        //true면 회원가입 가능
        //사용가능한 닉네임
        if (userService.checknick(requestDto)) {
            if(userService.checkid(requestDto)){
                //회원등록
                userService.save(requestDto);
                return "true";
            }
            else{
                return "idfalse";
            }

        }
        //사용불가능 닉네임
        else{
            return "nicknamefalse";
        }

    }


    //로그인
    @PostMapping("/api/user/login")
    public String login(@RequestBody UserLoginDto userLoginDto, HttpSession session) {
        if (userService.login(userLoginDto)) {
            session.setAttribute(LOGIN_USERID,userLoginDto.getUserid());
            return "true";
        } else {
            return "false";
        }
        
    }

    //로그인-아이디찾기
    @PostMapping("/api/user/finduserid")
    public String findid(@RequestBody UserSaveRequestDto requestDto) {
        //존재하지 않는 닉네임
        if (userService.checknick(requestDto)) {
            return "false";
        }
        //존재하는 닉네임
        else{
            //존재하지않는 핸드폰번호
            if((userService.findphone(requestDto)).equals(false)){
                return "false";
            }
            else{
                return userService.findphone(requestDto);
            }
        }

    }

    //로그인-비밀번호찾기
    @PostMapping("/api/user/finduserpw")
    public String findpw(@RequestBody UserSaveRequestDto requestDto) {
        //존재하지않는 아이디
        if (userService.checkid(requestDto)) {
            return "false";
        }
        //존재하는 아이디
        else{
            //존재하는 닉네임
            if((userService.findnick(requestDto)).equals(false)){
                return "false";
            }
            else{
                return userService.findnick(requestDto);
            }
        }

    }


    //회원수정-회원
    @PutMapping("/api/user/update/{userid}")
    public String update(@PathVariable String userid, @RequestBody
                       UserUpdateRequestDto requestDto){
        //존재하지 않는 닉네임
        if (userService.updatechecknick(userid,requestDto)) {
            return userService.update(userid, requestDto);
        }
        else{
            return "false";
        }

    }

    //관리자가 회원탈퇴-비밀번호확인
    //입력한 비밀번호가 회원의 비밀번호일치여부판단
    @DeleteMapping("/api/user/userfindAlldelete/{userid}")
    public void userfindAlldelete(@PathVariable String userid) {
        userService.delete(userid);

    }

    //회원탈퇴-비밀번호확인
    //입력한 비밀번호가 회원의 비밀번호일치여부판단
    @DeleteMapping("/api/user/check/{userid}")
    public String userdelete(@PathVariable String userid, @RequestBody UserLoginDto userLoginDto,
                             HttpSession session) {
        if (userService.checkpw(userid,userLoginDto)) {
            //일치하면 delete, session userid는 null로 변경
            userService.userdelete(userid);
            session.setAttribute(LOGIN_USERID,null);
            return "true";
        } else {
            return "false";
        }

    }

}
