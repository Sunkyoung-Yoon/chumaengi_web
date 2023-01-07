package com.project.springboot.domain.posts;

import com.project.springboot.domain.BaseTimeEntity;
import com.project.springboot.domain.comments.Answer;
import com.project.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter //클래스내 모든 필드의 Getter메소드 자동생성
@NoArgsConstructor //기본생성자 자동추가 public Posts(){}와 같은효과
@Entity //테이블과 링크될 클래스임을 나타냄 ex)SalesManager.java -> sales_manager table
@Table(name="question")
public class Question extends BaseTimeEntity {
    @Id //해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Pk의 생성규칙

    //게시글번호
    @Column(name="qid")
    private Long qid;
    //제목
    @Column(name="title", nullable = false)
    private String title;
    //내용
    @Column(name="content",columnDefinition="TEXT",nullable = false)
    private String content;
    //글쓴이
    @Column(name = "writer",nullable = false)
    private String writer;
    //조회수
    @Column(name="view", columnDefinition = "integer default 0")
    private int view;

    @Column(name="filename")
    private String filename;

    @Column(name="filepath")
    private String filepath;




    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="uid")
    private User user;

    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Answer> answer;


    @Builder //해당 클래스의 빌더패턴클래스 생성, 생성자 상단에 선언시 생성자레 포함된 필드만 빌더에 포함
    public Question(String title, String content, String writer, int view,
                 User user, String filename, String filepath) {
        this.title = title;
        this.content = content;
        this.writer=writer;
        this.view=view;
        this.user=user;
        this.filename=filename;
        this.filepath=filepath;
    }


    public void update(String title, String content,String filename,String filepath){
        this.title=title;
        this.content=content;
        this.filename=filename;
        this.filepath=filepath;
    }

    public void imagedelete(String filename,String filepath){
        this.filename=filename;
        this.filepath=filepath;
    }
}
