package com.project.springboot.web;

import com.project.springboot.web.dto.BoardNoticeUpdateRequestDto;
import com.project.springboot.service.posts.BoardNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardNoticeService boardNoticeService;

    //글 삭제
    @DeleteMapping("/api/boardNotice/delete/{bid}")
    public Long delete(@PathVariable Long bid){
        boardNoticeService.delete(bid);
        return bid;
    }
    //이미지 삭제
    @DeleteMapping("/api/boardNotice/delete/{bid}/image")
    public Long imagedelete(@PathVariable Long bid, BoardNoticeUpdateRequestDto requestDto){
        boardNoticeService.imagedelete(bid,requestDto);
        return bid;
    }
}
