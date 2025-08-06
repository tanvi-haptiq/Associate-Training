package com.assignment6.spring.services;

import com.assignment6.spring.models.Post;
import com.assignment6.spring.models.User;
import com.assignment6.spring.repository.PostRepository;
import com.assignment6.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost( Post post, Long authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        post.setAuthor(author);
        return postRepository.save(post);
    }

    public Page<Post> getPosts(String author, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return author != null
                ? postRepository.findByAuthor_Name(author, pageable)
                : postRepository.findAll(pageable);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}