package com.project.springboot.domain.comments;

import com.project.springboot.domain.BaseTimeEntity;
import com.project.springboot.domain.posts.Question;
import com.project.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스내 모든 필드의 Getter메소드 자동생성
@NoArgsConstructor //기본생성자 자동추가 public Posts(){}와 같은효과
@Entity //테이블과 링크될 클래스임을 나타냄 ex)SalesManager.java -> sales_manager table
@Table(name="answer")
public class Answer extends BaseTimeEntity {
    @Id //해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Pk의 생성규칙

    //댓글번호
    @Column(name="id")
    private Long id;

    //내용
    @Column(name="comment",columnDefinition="TEXT",nullable = false)
    private String comment;

    //글쓴이
    @Column(name = "writer",nullable = false)
    private String writer;

    //댓글 사용자 N:1
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="uid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qid")
    private Question question;

    @Builder //해당 클래스의 빌더패턴클래스 생성, 생성자 상단에 선언시 생성자레 포함된 필드만 빌더에 포함
    public Answer(String comment, String writer, User user, Question question) {
        this.comment = comment;
        this.writer=writer;
        this.user=user;
        this.question=question;
    }
}
