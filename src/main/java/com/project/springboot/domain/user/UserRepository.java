package com.project.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
    @Query("SELECT u FROM User u ORDER BY u.uid ")
    List<User> findAllDesc();
    User findByuserid(String userid);

    User findBynickname(String nickname);

    User findByphone(String phone);
}