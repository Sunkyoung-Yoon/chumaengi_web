package com.project.springboot.web;

import com.project.springboot.web.dto.InformationUpdateRequestDto;
import com.project.springboot.service.posts.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class InformationApiController {
    private final InformationService informationService;


    //글 삭제
    @DeleteMapping("/api/information/delete/{iid}")
    public Long delete(@PathVariable Long iid){
        informationService.delete(iid);
        return iid;
    }

    //이미지 삭제
    @DeleteMapping("/api/information/delete/{iid}/image")
    public Long imagedelete(@PathVariable Long iid, InformationUpdateRequestDto requestDto){
        informationService.imagedelete(iid,requestDto);
        return iid;
    }
}
