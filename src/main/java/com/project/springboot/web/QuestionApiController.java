package com.project.springboot.web;

import com.project.springboot.service.posts.QuestionService;
import com.project.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Controller
public class QuestionApiController {

    private final QuestionService questionService;

    //질문 삭제
    @DeleteMapping("/api/question/delete/{qid}")
    public Long delete(@PathVariable Long qid){
        questionService.delete(qid);
        return qid;
    }

    @DeleteMapping("/api/question/delete/{qid}/image")
    public Long imagedelete(@PathVariable Long qid, QuestionUpdateRequestDto requestDto){
        questionService.imagedelete(qid,requestDto);
        return qid;
    }

}
