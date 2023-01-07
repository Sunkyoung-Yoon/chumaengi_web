package com.project.springboot.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InformationRepository extends JpaRepository<Information,Long> {
    @Query("SELECT i FROM Information i ORDER BY i.iid DESC")
    Page<Information> findAllDesc(Pageable pageable);

    Information findByiid(Long iid);

    @Modifying
    @Query("update Information i set i.view = i.view + 1 where i.iid = :iid")
    int updateView(@Param("iid")Long iid);

    @Query(value = "SELECT i FROM Information i WHERE i.title LIKE %:keyword% OR i.content LIKE %:keyword% OR i.writer LIKE %:keyword% ORDER BY i.iid DESC")
    Page<Information> findAllSearch(@Param("keyword") String keyword, Pageable pageable);
}
