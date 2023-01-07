package com.project.springboot.web.dto;

import com.project.springboot.domain.comments.Comments;
import com.project.springboot.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class CommentsResponseDto {
    private Long id;
    private String comment;
    private String writer;

    private LocalDateTime modifiedDate;

    private User user;


    /* Entity -> Dto*/
    public CommentsResponseDto(Comments entity) {
        this.id = entity.getId();
        this.comment = entity.getComment();
        this.writer = entity.getWriter();
        this.modifiedDate=entity.getModifiedDate();
        this.user=entity.getUser();
    }
}
