package com.project.springboot.web;

import com.project.springboot.domain.posts.Question;
import com.project.springboot.service.posts.AnswerService;
import com.project.springboot.service.posts.QuestionService;
import com.project.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QnAController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    //qna게시판조회
    @GetMapping("/question/list")
    public String questionlist(Model model, @PageableDefault(page=0,size=10)
    Pageable pageable, String keyword) {
        Page<Question> list=null;
        if (keyword == null) {
            list = questionService.findAllDesc(pageable);
        }
        else{
            list = questionService.serchQuestion(keyword,pageable);
        }

        //pageable의 페이지 시작은 0이므로 1로 보기위해 +1
        int nowPage=list.getPageable().getPageNumber()+1;
        //page가 음수가 될 경우 1페이지
        int startPage=Math.max(nowPage-4,1);
        //page가 토탈페이지수를 넘는 경우 마지막페이지
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("qnaList",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "posts/question_list";
    }

    //질문등록 페이지로 이동
    @GetMapping("/question/save")
    public String questionsave(){
        return "posts/question_save";

    }

    //question 상세조회
    @GetMapping("/question/detail/{qid}")
    public String questiondetail(@PathVariable Long qid
            , Model model){
        QuestionDetailDto questionDetailDto = questionService.findByqid(qid);
        List<AnswerResponseDto> answer = questionDetailDto.getAnswer();
        if(answer != null && !answer.isEmpty()){
            model.addAttribute("answerList",answer);
        }
        questionService.updateView(qid); //조회수 1증가
        model.addAttribute("question",questionDetailDto);
        return "posts/question_detail";
    }

    //question 수정 페이지
    @GetMapping("/question/update/{qid}")
    public String questionupdate(@PathVariable Long qid, Model model){
        QuestionDetailDto questionDetailDto = questionService.findByqid(qid);
        model.addAttribute("question",questionDetailDto);
        return "posts/question_update";
    }

    //댓글 삭제
    @GetMapping("/api/question/delete/comment/{aid}/{qid}")
    public String delete(@PathVariable Long aid, @PathVariable Long qid){
        answerService.delete(aid);
        return "redirect:/question/detail/"+qid;
    }

    //질문 등록
    @PostMapping("/api/question/save/{userid}")
    public String save(@PathVariable String userid, QuestionSaveRequestDto requestDto,
                       MultipartFile file) throws Exception{
        questionService.save(userid,requestDto,file);
        return "redirect:/question/list";
    }

    //질문수정
    @PostMapping("/api/question/update/{qid}")
    public String update(@PathVariable Long qid, QuestionUpdateRequestDto requestDto,
                         MultipartFile file) throws Exception {
        questionService.update(qid, requestDto, file);
        return "redirect:/question/detail/" + qid;
    }

}
