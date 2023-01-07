package com.project.springboot.web.dto;

import com.project.springboot.domain.comments.Answer;
import com.project.springboot.domain.posts.Question;
import com.project.springboot.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AnswerRequestDto {
    @NotBlank(message = "내용을 작성해주세요.")
    private String comment;

    private String writer;
    private User user;
    private Question question;

    public Answer toEntity() {
        Answer answer = Answer.builder()
                .comment(comment)
                .writer(writer)
                .user(user)
                .question(question)
                .build();
        return answer;
    }
}
