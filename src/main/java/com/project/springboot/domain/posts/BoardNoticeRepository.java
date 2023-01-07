package com.project.springboot.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardNoticeRepository extends JpaRepository<BoardNotice,Long> {
    @Query("SELECT b FROM BoardNotice b ORDER BY b.bid DESC")
    Page<BoardNotice> findAllDesc(Pageable pageable);

    BoardNotice findBybid(Long bid);

    @Modifying
    @Query("update BoardNotice b set b.view = b.view + 1 where b.bid = :bid")
    int updateView(@Param("bid")Long bid);

    @Query(value = "SELECT b FROM BoardNotice b WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword% OR b.writer LIKE %:keyword% ORDER BY b.bid DESC")
    Page<BoardNotice> findAllSearch(@Param("keyword") String keyword,Pageable pageable);
}
