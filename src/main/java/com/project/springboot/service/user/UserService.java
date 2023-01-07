package com.project.springboot.service.user;

import com.project.springboot.domain.user.User;
import com.project.springboot.domain.user.UserRepository;
import com.project.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    //회원등록
    //save를 할때는 requestDto를 entity로, find를 할 때는 entity를 responseDto로 전환
    @Transactional
    public Long save(UserSaveRequestDto requestDto){

        return userRepository.save(requestDto.toEntity()).getUid();
    }

    //boolean exist
    @Transactional
    public boolean checkid(UserSaveRequestDto requestDto){
        //입력한 아이디와 일치하는 user받아옴
        User usercheckid = userRepository.findByuserid(requestDto.getUserid());
        //true면 회원가입가능
        //null이면 사용가능한 아이디
        if(usercheckid==null){
            return true;
        }else{
            return false;
        }
    }

    //회원가입, 아이디찾기, 비밀번호 찾기(닉네인 존재여부)
    @Transactional
    public boolean checknick(UserSaveRequestDto requestDto){
        //입력한 닉네임과 일치하는 user받아옴
        User userchecknick = userRepository.findBynickname(requestDto.getNickname());
        //true면 회원가입가능
        //null이면 사용가능한 닉네임
        if(userchecknick==null){
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public boolean login(UserLoginDto userLoginDto) {
        // User타입의 객체 생성 후 jpa의 findBy 메서드 호출 및 정보 저장
        // UserLoginDto의 userid를 보내 값을 User에 담는것임.
        User user= userRepository.findByuserid(userLoginDto.getUserid());
        if (user!=null) {
            // 로그인을 시도한 데이터의 비밀번호와 jpa에서 받아온 데이터의 비밀번호를 비교
            if(user.getPassword().equals(userLoginDto.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //로그인-비밀번호찾기닉네임-비밀번호값 리턴
    @Transactional
    public String findnick(UserSaveRequestDto requestDto){
        //입력한 이름과 일치하는 user받아옴
        User userfindnick = userRepository.findBynickname(requestDto.getNickname());
        if(userfindnick!=null){
            String password = userfindnick.getPassword();
           return password;
        }else{
            return "false";
        }
    }
    //로그인-아이디찾기폰번호-아이디값 리턴
    @Transactional
    public String findphone(UserSaveRequestDto requestDto){
        User userfindphone = userRepository.findByphone(requestDto.getPhone());
        if(userfindphone!=null){
            String userid=userfindphone.getUserid();
            return userid;
        }else{
            return "false";
        }
    }


    //회원조회-관리자
    @Transactional(readOnly = true)
    public List<UserDetailDto> findAllDesc(){
        return userRepository.findAllDesc().stream()
                //.map(user->new UserDatailDto(user))
                .map(UserDetailDto::new)
                .collect(Collectors.toList());
    }


    //마이페이지
    @Transactional
    public UserDetailDto findByuserid(String userid){
        User entity = userRepository.findByuserid(userid);
        return new UserDetailDto(entity);
    }




    //회원수정

    @Transactional
    public boolean updatechecknick(String userid, UserUpdateRequestDto requestDto){
        User user = userRepository.findByuserid(userid);
        if(user.getNickname().equals(requestDto.getNickname())){
            return true;
        }
        else{
            User updatenick = userRepository.findBynickname(requestDto.getNickname());
            //사용가능한 닉네임
            if(updatenick==null){
                return true;
                //이미 사용중인 닉네임
            }else{
                return false;
            }
        }
    }
    @Transactional
    public String update(String userid, UserUpdateRequestDto requestDto){
        User user = userRepository.findByuserid(userid);
        user.update(requestDto.getPassword(), requestDto.getNickname(), requestDto.getPhone(),
                requestDto.getEmail(), requestDto.getAddress());

        return userid;
    }

    //회원탈퇴-비밀번호확인
    @Transactional
    public boolean checkpw(String userid, UserLoginDto userLoginDto){
        User user = userRepository.findByuserid(userid);
        //입력한 비밀번호가 회원의 비밀번호와 일치->true반환
        if (user.getPassword().equals(userLoginDto.getPassword())) {
            return true;
        } else {
            return false;
        }
    }



    //삭제-회원
    public void userdelete(String userid){
        User user = userRepository.findByuserid(userid);
        userRepository.delete(user);
    }



    //삭제-관리자
    @Transactional
    public void delete(String userid){
        User user = userRepository.findByuserid(userid);
        userRepository.delete(user);
    }
}
