package com.project.springboot.service.posts;

import com.project.springboot.domain.comments.Comments;
import com.project.springboot.domain.comments.CommentsRepository;
import com.project.springboot.domain.posts.Information;
import com.project.springboot.domain.posts.InformationRepository;
import com.project.springboot.domain.user.User;
import com.project.springboot.domain.user.UserRepository;
import com.project.springboot.web.dto.CommentsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final InformationRepository informationRepository;
    private final UserRepository userRepository;

    private final CommentsRepository commentsRepository;

    //댓글 등록
    @Transactional
    public Long save(String userid, Long iid, CommentsRequestDto requestDto) {
        User user = userRepository.findByuserid(userid);
        Information information = informationRepository.findByiid(iid);

        requestDto.setUser(user);
        requestDto.setWriter(user.getNickname());
        requestDto.setInformation(information);

        return commentsRepository.save(requestDto.toEntity()).getId();
    }

    //댓글 삭제
    @Transactional
    public void delete(Long cid){
        Comments comments = commentsRepository.findByid(cid);
        commentsRepository.delete(comments);
    }
}
