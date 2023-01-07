package com.project.springboot.web;

import com.project.springboot.web.dto.CommentsRequestDto;
import com.project.springboot.service.posts.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {
    private final CommentsService commentsService;

    //댓글등록
    @PostMapping("/api/information/save/{userid}/comment/{iid}")
    public Long save(@PathVariable String userid, @PathVariable Long iid, @RequestBody CommentsRequestDto requestDto) {
        return commentsService.save(userid,iid,requestDto);
    }

}
