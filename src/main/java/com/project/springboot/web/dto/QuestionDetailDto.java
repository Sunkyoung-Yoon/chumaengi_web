package com.project.springboot.web.dto;

import com.project.springboot.domain.posts.Question;
import com.project.springboot.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class QuestionDetailDto {
    private Long qid;
    private String title;

    private String content;
    private String writer;
    private int view;
    private LocalDateTime modifiedDate;

    private User user;
    private List<AnswerResponseDto> answer;

    private String filepath;

    public QuestionDetailDto (Question entity) {
        this.qid = entity.getQid();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.view = entity.getView();
        this.modifiedDate = entity.getModifiedDate();
        this.user = entity.getUser();
        //answer 필드의 List 타입을 Dto 클래스로해서 엔티티간 무한 참조를 방지
        this.answer = entity.getAnswer().stream()
                .map(AnswerResponseDto::new).collect(Collectors.toList());
        this.filepath=entity.getFilepath();
    }
}
