package com.project.springboot.service.posts;

import com.project.springboot.domain.posts.Information;
import com.project.springboot.domain.posts.InformationRepository;
import com.project.springboot.domain.user.User;
import com.project.springboot.domain.user.UserRepository;
import com.project.springboot.web.dto.InformationDetailDto;
import com.project.springboot.web.dto.InformationSaveRequestDto;
import com.project.springboot.web.dto.InformationUpdateRequestDto;
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
public class InformationService {

    private final InformationRepository informationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(String userid, InformationSaveRequestDto requestDto,
                     MultipartFile file) throws Exception {
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

        informationRepository.save(requestDto.toEntity()).getIid();
    }

    //정보공유게시판 조회
    @Transactional(readOnly = true)
    public Page<Information> findAllDesc(Pageable pageable){
        return informationRepository.findAllDesc(pageable);
    }

    //정보공유게시판 상세조회
    @Transactional
    public InformationDetailDto findByiid(Long iid){
        Information entity = informationRepository.findByiid(iid);
        return new InformationDetailDto(entity);
    }

    @Transactional
    public int updateView(Long iid){
        return informationRepository.updateView(iid);
    }

    //글 수정
    @Transactional
    public void update(Long iid, InformationUpdateRequestDto requestDto,
                       MultipartFile file)throws Exception{
        Information information = informationRepository.findByiid(iid);

        if(!file.isEmpty())
        {
            if(information.getFilename()!=null && information.getFilename()!=""){
                new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                        +information.getFilepath()).delete();
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
            requestDto.setFilename(information.getFilename());
            requestDto.setFilepath(information.getFilepath());
        }
        information.update(requestDto.getTitle(),requestDto.getContent(),requestDto.getFilename(),
                requestDto.getFilepath());
    }

    //이미지 삭제
    @Transactional
    public void imagedelete(Long iid, InformationUpdateRequestDto requestDto){
        Information information = informationRepository.findByiid(iid);
        new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                +information.getFilepath()).delete();
        requestDto.setFilename(null);
        requestDto.setFilepath(null);

        information.imagedelete(requestDto.getFilename(), requestDto.getFilepath());

    }

    //글 삭제
    @Transactional
    public void delete(Long iid){
        Information information = informationRepository.findByiid(iid);
        if(information.getFilename()!=null && information.getFilename()!="") {
            new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                    +information.getFilepath()).delete();
        }
        informationRepository.delete(information);
    }

    //정보공유게시판 검색(제목, 내용, 작성자)
    @Transactional
    public Page<Information> serchInformation(String keyword, Pageable pageable){
        return informationRepository.findAllSearch(keyword,pageable);
    }
}
