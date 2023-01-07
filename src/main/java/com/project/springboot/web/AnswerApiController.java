package com.project.springboot.web;

import com.project.springboot.web.dto.AnswerRequestDto;
import com.project.springboot.service.posts.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {
    private final AnswerService answerService;

    //댓글등록
    @PostMapping("/api/question/save/{userid}/comment/{qid}")
    public Long save(@PathVariable String userid, @PathVariable Long qid, @RequestBody AnswerRequestDto requestDto) {
        return answerService.save(userid,qid,requestDto);
    }

}
