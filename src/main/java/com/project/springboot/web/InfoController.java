package com.project.springboot.web;

import com.project.springboot.domain.posts.Information;
import com.project.springboot.service.posts.CommentsService;
import com.project.springboot.service.posts.InformationService;
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
public class InfoController {

    private final InformationService informationService;

    private final CommentsService commentsService;
    // 정보공유 게시판조회
    @GetMapping("/information/list")
    public String informationlist(Model model, @PageableDefault(page=0,size=10)
                                  Pageable pageable, String keyword) {
        Page<Information> list=null;
        if (keyword == null) {
            list = informationService.findAllDesc(pageable);
        }
        else{
            list = informationService.serchInformation(keyword,pageable);
        }

        //pageable의 페이지 시작은 0이므로 1로 보기위해 +1
        int nowPage=list.getPageable().getPageNumber()+1;
        //page가 음수가 될 경우 1페이지
        int startPage=Math.max(nowPage-4,1);
        //page가 토탈페이지수를 넘는 경우 마지막페이지
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("infoList",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "posts/information_list";
    }


    //글등록 페이지로 이동
    @GetMapping("/information/save")
    public String informationsave(){
        return "posts/information_save";

    }

    //정보공유게시판 상세조회
    @GetMapping("/information/detail/{iid}")
    public String informationdetail(@PathVariable Long iid, Model model){
        InformationDetailDto informationDetailDto = informationService.findByiid(iid);
        List<CommentsResponseDto> comments = informationDetailDto.getComments();
        if(comments != null && !comments.isEmpty()){
            model.addAttribute("commentsList",comments);
        }

        informationService.updateView(iid); //조회수 1증가
        model.addAttribute("information",informationDetailDto);
        return "posts/information_detail";
    }

    //글 수정 페이지
    @GetMapping("/information/update/{iid}")
    public String informationupdate(@PathVariable Long iid, Model model){
        InformationDetailDto informationDetailDto = informationService.findByiid(iid);
        model.addAttribute("information",informationDetailDto);
        return "posts/information_update";
    }


    //댓글 삭제
    @GetMapping("/api/information/delete/comment/{cid}/{iid}")
    public String delete(@PathVariable Long cid, @PathVariable Long iid){
        commentsService.delete(cid);
        return "redirect:/information/detail/"+iid;
    }


    //글 등록
    @PostMapping("/api/information/save/{userid}")
    public String save(@PathVariable String userid, InformationSaveRequestDto requestDto,
                     MultipartFile file) throws Exception {
        informationService.save(userid,requestDto,file);
        return "redirect:/information/list";
    }
    //글 수정
    @PostMapping("/api/information/update/{iid}")
    public String update(@PathVariable Long iid,
    InformationUpdateRequestDto requestDto, MultipartFile file) throws Exception{
        informationService.update(iid,requestDto,file);
        return "redirect:/information/detail/" + iid;
    }

}
