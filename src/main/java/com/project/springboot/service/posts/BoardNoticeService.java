package com.project.springboot.service.posts;

import com.project.springboot.domain.posts.BoardNotice;
import com.project.springboot.domain.posts.BoardNoticeRepository;
import com.project.springboot.domain.user.User;
import com.project.springboot.domain.user.UserRepository;
import com.project.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardNoticeService {
    private final BoardNoticeRepository boardNoticeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(String userid, BoardNoticeSaveRequestDto requestDto
            , MultipartFile file) throws Exception {
        User user = userRepository.findByuserid(userid);
        requestDto.setUser(user);
        requestDto.setWriter(user.getNickname());

        if(!file.isEmpty()){
            UUID uuid = UUID.randomUUID();

            String fileName = uuid+"+"+file.getOriginalFilename();

            String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/files";

            File saveFile = new File(projectPath,fileName);

            file.transferTo(saveFile);

            requestDto.setFilename(fileName);
            requestDto.setFilepath("/files/"+fileName);
        }
        boardNoticeRepository.save(requestDto.toEntity()).getBid();
    }

    //공지사항 조회
    @Transactional(readOnly = true)
    public Page<BoardNotice> findAllDesc(Pageable pageable){
        return boardNoticeRepository.findAllDesc(pageable);
    }

    //공지사항 상세조회
    @Transactional
    public BoardNoticeDetailDto findBybid(Long bid){
        BoardNotice entity = boardNoticeRepository.findBybid(bid);
        return new BoardNoticeDetailDto(entity);
    }

    @Transactional
    public int updateView(Long bid){
        return boardNoticeRepository.updateView(bid);
    }

    //공지사항 수정
    @Transactional
    public void update(Long bid, BoardNoticeUpdateRequestDto requestDto,
                       MultipartFile file) throws Exception{
        BoardNotice boardNotice = boardNoticeRepository.findBybid(bid);

        if(!file.isEmpty()){
            if(boardNotice.getFilename()!=null && boardNotice.getFilename()!=""){
                new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                        +boardNotice.getFilepath()).delete();
            }

            UUID uuid = UUID.randomUUID();

            String fileName = uuid+"+"+file.getOriginalFilename();

            String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/files";

            File saveFile = new File(projectPath,fileName);

            file.transferTo(saveFile);
            requestDto.setFilename(fileName);
            requestDto.setFilepath("/files/"+fileName);
        }
        else{
            requestDto.setFilename(boardNotice.getFilename());
            requestDto.setFilepath(boardNotice.getFilepath());
        }
        boardNotice.update(requestDto.getTitle(),requestDto.getContent(),requestDto.getFilename(),
                requestDto.getFilepath());
    }

    //이미지 삭제
    @Transactional
    public void imagedelete(Long bid, BoardNoticeUpdateRequestDto requestDto){
        BoardNotice boardNotice = boardNoticeRepository.findBybid(bid);
        new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                +boardNotice.getFilepath()).delete();
        requestDto.setFilename(null);
        requestDto.setFilepath(null);

        boardNotice.imagedelete(requestDto.getFilename(), requestDto.getFilepath());

    }

    //공지사항 삭제
    @Transactional
    public void delete(Long bid){
        BoardNotice boardNotice = boardNoticeRepository.findBybid(bid);
        if(boardNotice.getFilename()!=null && boardNotice.getFilename()!="") {
            new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                    +boardNotice.getFilepath()).delete();
        }
        boardNoticeRepository.delete(boardNotice);
    }

    //공지사항 검색(제목, 내용, 작성자)
    @Transactional
    public Page<BoardNotice> serchBoardNotice(String keyword, Pageable pageable) {
        return boardNoticeRepository.findAllSearch(keyword, pageable);
    }
}
