package com.project.springboot.web.dto;


import com.project.springboot.domain.comments.Answer;
import com.project.springboot.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class AnswerResponseDto {
    private Long id;
    private String comment;
    private String writer;

    private LocalDateTime modifiedDate;

    private User user;


    /* Entity -> Dto*/
    public AnswerResponseDto(Answer entity) {
        this.id = entity.getId();
        this.comment = entity.getComment();
        this.writer = entity.getWriter();
        this.modifiedDate=entity.getModifiedDate();
        this.user=entity.getUser();
    }
}
