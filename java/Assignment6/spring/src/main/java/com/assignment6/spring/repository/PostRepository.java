package com.assignment6.spring.repository;

//package com.example.blog.repository;

import com.assignment6.spring.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByAuthor_Name(String authorName, Pageable pageable);
}

