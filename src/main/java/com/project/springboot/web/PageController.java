package com.project.springboot.web;

import com.project.springboot.web.dto.UserDetailDto;
import com.project.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final UserService userService;

    @RequestMapping (value="/")
        public String mainView() {
        return "user/index";
        }

    //회원등록페이지로 이동
    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }


    //로그인 페이지로 이동
    @GetMapping("/user/login")
    public String login(){return "user/login";}

    //아이디찾기 페이지로 이동
    @GetMapping("/user/finduserid")
    public String finduserid(){
        return "user/finduserid";
    }

    //비밀번호 찾기 페이지로 이동
    @GetMapping("/user/finduserpw")
    public String finduserpassword(){
        return "user/finduserpassword";
    }

    //로그아웃후 메인페이지로 이동
    //로그아웃
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "user/index";

    }

    //회원조회-관리자
    @GetMapping("/user/userfindAll")
    public String findAllDesc(Model model) {
        model.addAttribute("userList", userService.findAllDesc());
        return "user/userfindAll";
    }

    //회원삭제-관리자

    @GetMapping("/user/userfindAlldelete/{userid}")
    public String delete(@PathVariable String userid, Model model){
        UserDetailDto userDetailDto = userService.findByuserid(userid);
        model.addAttribute("userList", userDetailDto);
        return "user/userfindAlldelete";
    }


    //회원조회-회원마이페이지
    @GetMapping("/user/mypage/{userid}")
    public String findByuserid(@PathVariable String userid, Model model) {
        UserDetailDto userDetailDto = userService.findByuserid(userid);
        model.addAttribute("usermyList", userDetailDto);
        return "user/mypage";

    }

    //회원수정-회원마이페이지
    @GetMapping("/user/update/{userid}")
    public String userupdate(@PathVariable String userid, Model model){
        UserDetailDto userDetailDto = userService.findByuserid(userid);
        model.addAttribute("usermyList",userDetailDto);
        return "user/userupdate";
    }

    //회원탈퇴-회원수정페이지에서 탈퇴 페이지로 이동
    @GetMapping("/user/delete/{userid}")
    public String userdelete(@PathVariable String userid, Model model){
            UserDetailDto userDetailDto = userService.findByuserid(userid);
            model.addAttribute("usermyList",userDetailDto);
        return "user/userdelete";}



}
