package com.project.springboot.domain.user;

import com.project.springboot.domain.BaseTimeEntity;
import com.project.springboot.domain.posts.BoardNotice;
import com.project.springboot.domain.posts.Information;
import com.project.springboot.domain.posts.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.util.*;

/*
@Entity는 JPA의 어노테이션이고, @Getter와 @NoArgsConstructor은 롬북의 어노테이션
롬북은 코드를 단순화시켜주지만 필수 어노테이션은 아님
주요 어노테이션을 @Entity를 클래스에 가까이 두고, 롬북 어노테이션은 그 위로 둠
코틀린 등의 새 언어 전환으로 롬북이 더이상 필요없을 경우 쉽게 삭제 가능
*/
@Getter //클래스내 모든 필드의 Getter메소드 자동생성
@NoArgsConstructor//기본생성자 자동추가 public Posts(){}와 같은효과
@Entity //테이블과 링크될 클래스임을 나타냄 ex)SalesManager.java -> sales_manager table
@Table(name="user")
public class User extends BaseTimeEntity {

    @Id //해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Pk의 생성규칙
    /*
    @Column은 테이블의 칼럼을 나타내며 선언하지않아도 해당 클래스의 필드는 모두 컬럼
    기본값이외에 추가로 변경이 필요항 옵션이 있을때 사용
     */
    //사용자번호
    @Column(name="uid", nullable = false)
    private Long uid;
    //아이디
    @Column(name="userid", unique = true, nullable = false)
    private String userid;
    //비밀번호
    @Column(name="password",nullable = false)
    private String password;
    //이름
    @Column(name="username", nullable = false)
    private String username;
    //닉네임
    @Column(name="nickname", nullable = false)
    private String nickname;
    //폰번호
    @Column(name="phone",nullable = false)
    private String phone;
    //이메일
    @Column(name="email",nullable = false)
    private String email;
    //주소
    @Column(name="address",nullable = false)
    private String address;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Information> information;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardNotice> boardNotices;
    //권한

    @Builder //해당 클래스의 빌더패턴클래스 생성, 생성자 상단에 선언시 생성자레 포함된 필드만 빌더에 포함
    public User(String userid, String password, String username, String nickname, String phone,
                  String email, String address){
        this.userid=userid;
        this.password=password;
        this.username=username;
        this.nickname=nickname;
        this.phone=phone;
        this.email=email;
        this.address=address;
    }

    public void update(String password, String nickname, String phone, String email,
                       String address){
        this.password=password;
        this.nickname=nickname;
        this.phone=phone;
        this.email=email;
        this.address=address;
    }



}
