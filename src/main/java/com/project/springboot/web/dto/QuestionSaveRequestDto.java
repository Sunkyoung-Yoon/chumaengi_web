package com.project.springboot.web.dto;

import com.project.springboot.domain.posts.Question;
import com.project.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
public class QuestionSaveRequestDto {
    @NotBlank(message = "제목을 작성해주세요.")
    private String title;
    @NotBlank(message = "내용을 작성해주세요.")
    private String content;
    @NotBlank(message = "작성자를 작성해주세요.")
    private String writer;
    @NotBlank(message = "조회수를 작성해주세요.")
    private int view;
    @NotBlank(message = "사용자번호를 작성해주세요.")
    private User user;

    private String filename;
    private String filepath;

    @Builder
    public QuestionSaveRequestDto(String title, String content, String writer,
                                  int view, User user, String filename, String filepath){

        this.title=title;
        this.content=content;
        this.writer=writer;
        this.view=view;
        this.user=user;
        this.filename=filename;
        this.filepath=filepath;
    }

    public Question toEntity(){
        return Question.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .view(0)
                .user(user)
                .filename(filename)
                .filepath(filepath)
                .build();
    }

}
