package com.project.springboot.web.dto;

import com.project.springboot.domain.posts.BoardNotice;
import com.project.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class BoardNoticeSaveRequestDto {
    @NotBlank(message = "제목을 작성해주세요.")
    private String title;
    @NotBlank(message = "내용을 작성해주세요.")
    private String content;
    @NotBlank(message = "작성자를 작성해주세요.")
    private String writer;

    private String filename;
    private String filepath;
    @NotBlank(message = "조회수를 작성해주세요.")
    private int view;
    @NotBlank(message = "사용자번호를 작성해주세요.")
    private User user;

    @Builder
    public BoardNoticeSaveRequestDto(String title,String content, String writer,
                                  String filename,String filepath,
                                  int view, User user){

        this.title=title;
        this.content=content;
        this.writer=writer;
        this.filename=filename;
        this.filepath=filepath;
        this.view=view;
        this.user=user;
    }

    public BoardNotice toEntity(){
        return BoardNotice.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .filename(filename)
                .filepath(filepath)
                .view(0)
                .user(user)
                .build();
    }
}
