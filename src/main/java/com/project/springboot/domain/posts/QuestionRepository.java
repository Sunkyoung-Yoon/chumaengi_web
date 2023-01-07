package com.project.springboot.domain.posts;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT q FROM Question q ORDER BY q.qid DESC")
    Page<Question> findAllDesc(Pageable pageable);

    Question findByqid(Long qid);

    @Modifying
    @Query("update Question q set q.view = q.view + 1 where q.qid = :qid")
    int updateView(@Param("qid")Long qid);

    @Query(value = "SELECT q FROM Question q WHERE q.title LIKE %:keyword% OR q.content LIKE %:keyword% OR q.writer LIKE %:keyword% ORDER BY q.qid DESC")
    Page<Question> findAllSearch(@Param("keyword") String keyword,Pageable pageable);

}
