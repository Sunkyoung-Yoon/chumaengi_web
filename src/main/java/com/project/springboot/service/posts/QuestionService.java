package com.project.springboot.service.posts;


import com.project.springboot.domain.posts.QuestionRepository;
import com.project.springboot.domain.posts.Question;
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
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(String userid, QuestionSaveRequestDto questionSaveRequestDto
    , MultipartFile file) throws Exception{
        User user = userRepository.findByuserid(userid);
        questionSaveRequestDto.setUser(user);
        questionSaveRequestDto.setWriter(user.getNickname());

        if(!file.isEmpty()){
            UUID uuid = UUID.randomUUID();

            String fileName = uuid+"+"+file.getOriginalFilename();

            String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/files";

            File saveFile = new File(projectPath,fileName);

            file.transferTo(saveFile);

            questionSaveRequestDto.setFilename(fileName);
            questionSaveRequestDto.setFilepath("/files/"+fileName);
        }

        questionRepository.save(questionSaveRequestDto.toEntity()).getQid();
    }

    //qna게시판 조회
    @Transactional(readOnly = true)
    public Page<Question> findAllDesc(Pageable pageable){
        return questionRepository.findAllDesc(pageable);
    }

    //question 상세조회
    @Transactional
    public QuestionDetailDto findByqid(Long qid){
        Question entity = questionRepository.findByqid(qid);
        return new QuestionDetailDto(entity);
    }

    //조회수 증가
    @Transactional
    public int updateView(Long qid){
        return questionRepository.updateView(qid);
    }

    //질문 수정
    @Transactional
    public void update(Long qid, QuestionUpdateRequestDto requestDto,
                       MultipartFile file)throws Exception{
        Question question = questionRepository.findByqid(qid);

        if(!file.isEmpty())
        {
            if(question.getFilename()!=null && question.getFilename()!=""){
                new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"+question.getFilepath()).delete();
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
            requestDto.setFilename(question.getFilename());
            requestDto.setFilepath(question.getFilepath());
        }
        question.update(requestDto.getTitle(),requestDto.getContent(),requestDto.getFilename(),
                requestDto.getFilepath());

    }
    //이미지 삭제
    @Transactional
    public void imagedelete(Long qid, QuestionUpdateRequestDto requestDto){
        Question question = questionRepository.findByqid(qid);
        new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                +question.getFilepath()).delete();
        requestDto.setFilename(null);
        requestDto.setFilepath(null);

        question.imagedelete(requestDto.getFilename(), requestDto.getFilepath());

    }

    //질문 삭제
    @Transactional
    public void delete(Long qid){
        Question question = questionRepository.findByqid(qid);
        if(question.getFilename()!=null && question.getFilename()!="") {
            new File("C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static"
                    +question.getFilepath()).delete();
        }
        questionRepository.delete(question);
    }

    //질문게시판 검색(제목, 내용, 작성자)
    @Transactional
    public Page<Question> serchQuestion(String keyword,Pageable pageable){
        return questionRepository.findAllSearch(keyword,pageable);
    }

}
