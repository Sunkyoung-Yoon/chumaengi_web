package com.project.springboot.domain.comments;


import org.springframework.data.jpa.repository.JpaRepository;



public interface AnswerRepository extends JpaRepository<Answer,Long> {
    Answer findByid(Long id);

}
