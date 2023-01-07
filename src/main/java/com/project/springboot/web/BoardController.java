package com.project.springboot.web;

import com.project.springboot.domain.posts.BoardNotice;
import com.project.springboot.service.posts.BoardNoticeService;
import com.project.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNoticeService boardNoticeService;

    // 정보공유 게시판조회
    @GetMapping("/boardNotice/list")
    public String boardNoticelist(Model model, @PageableDefault(page=0,size=10)
    Pageable pageable, String keyword) {
        Page<BoardNotice> list=null;
        if (keyword == null) {
            list = boardNoticeService.findAllDesc(pageable);
        }
        else{
            list = boardNoticeService.serchBoardNotice(keyword,pageable);
        }

        //pageable의 페이지 시작은 0이므로 1로 보기위해 +1
        int nowPage=list.getPageable().getPageNumber()+1;
        //page가 음수가 될 경우 1페이지
        int startPage=Math.max(nowPage-4,1);
        //page가 토탈페이지수를 넘는 경우 마지막페이지
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("boardList",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "posts/boardnotice_list";
    }

    //글등록 페이지로 이동
    @GetMapping("/boardNotice/save")
    public String boardNoticesave(){
        return "posts/boardnotice_save";

    }

    //정보공유게시판 상세조회
    @GetMapping("/boardNotice/detail/{bid}")
    public String boardNoticedetail(@PathVariable Long bid, Model model){
        BoardNoticeDetailDto boardNoticeDetailDto = boardNoticeService.findBybid(bid);
        boardNoticeService.updateView(bid); //조회수 1증가
        model.addAttribute("board",boardNoticeDetailDto);
        return "posts/boardnotice_detail";
    }

    //글 수정 페이지
    @GetMapping("/boardNotice/update/{bid}")
    public String boardNoticeupdate(@PathVariable Long bid, Model model){
        BoardNoticeDetailDto boardNoticeDetailDto = boardNoticeService.findBybid(bid);
        model.addAttribute("board",boardNoticeDetailDto);
        return "posts/boardnotice_update";
    }


    //공지사항 등록
    @PostMapping("/api/boardNotice/save/{userid}")
    public String save(@PathVariable String userid, BoardNoticeSaveRequestDto requestDto,
                       MultipartFile file) throws Exception{
        boardNoticeService.save(userid,requestDto,file);
        return "redirect:/boardNotice/list";
    }

    //질문수정
    @PostMapping("/api/boardNotice/update/{bid}")
    public String update(@PathVariable Long bid, BoardNoticeUpdateRequestDto requestDto,
                         MultipartFile file) throws Exception {
        boardNoticeService.update(bid, requestDto, file);
        return "redirect:/boardNotice/detail/" + bid;
    }
}
