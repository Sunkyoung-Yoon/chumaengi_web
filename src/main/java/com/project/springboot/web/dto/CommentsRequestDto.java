package com.project.springboot.web.dto;

import com.project.springboot.domain.comments.Comments;
import com.project.springboot.domain.posts.Information;
import com.project.springboot.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CommentsRequestDto {
    @NotBlank(message = "내용을 작성해주세요.")
    private String comment;

    private String writer;
    private User user;
    private Information information;

    public Comments toEntity() {
        Comments comments = Comments.builder()
                .comment(comment)
                .writer(writer)
                .user(user)
                .information(information)
                .build();
        return comments;
    }
}
