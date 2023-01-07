package com.project.springboot.web.dto;

import com.project.springboot.domain.posts.BoardNotice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class BoardNoticeUpdateRequestDto {
    @NotBlank(message = "제목을 작성해주세요.")
    private String title;
    @NotBlank(message = "내용을 작성해주세요.")
    private String content;

    private String filename;
    private String filepath;

    @Builder
    public BoardNoticeUpdateRequestDto(String title,String content,
                                       String filename,String filepath){

        this.title=title;
        this.content=content;
        this.filename=filename;
        this.filepath=filepath;
    }

    public BoardNotice toEntity(){
        return BoardNotice.builder()
                .title(title)
                .content(content)
                .filename(filename)
                .filepath(filepath)
                .build();
    }
}
