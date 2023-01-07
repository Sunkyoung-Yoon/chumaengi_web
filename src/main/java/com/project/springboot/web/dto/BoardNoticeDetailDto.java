package com.project.springboot.web.dto;

import com.project.springboot.domain.posts.BoardNotice;
import com.project.springboot.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardNoticeDetailDto {
    private Long bid;
    private String title;

    private String content;
    private String writer;
    private int view;
    private LocalDateTime modifiedDate;
    private User user;

    private String filepath;

    public BoardNoticeDetailDto (BoardNotice entity){
        this.bid=entity.getBid();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.writer=entity.getWriter();
        this.view=entity.getView();
        this.modifiedDate=entity.getModifiedDate();
        this.user=entity.getUser();
        this.filepath=entity.getFilepath();
    }
}
