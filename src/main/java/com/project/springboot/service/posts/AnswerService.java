package com.project.springboot.service.posts;

import com.project.springboot.domain.comments.Answer;
import com.project.springboot.domain.comments.AnswerRepository;
import com.project.springboot.domain.posts.Question;
import com.project.springboot.domain.posts.QuestionRepository;
import com.project.springboot.domain.user.User;
import com.project.springboot.domain.user.UserRepository;
import com.project.springboot.web.dto.AnswerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    //댓글 등록
    @Transactional
    public Long save(String userid, Long qid, AnswerRequestDto requestDto) {
        User user = userRepository.findByuserid(userid);
        Question question = questionRepository.findByqid(qid);

        requestDto.setUser(user);
        requestDto.setWriter(user.getNickname());
        requestDto.setQuestion(question);

        return answerRepository.save(requestDto.toEntity()).getId();
    }

    //댓글 삭제
    @Transactional
    public void delete(Long aid){
        Answer answer = answerRepository.findByid(aid);
        answerRepository.delete(answer);
    }

}
