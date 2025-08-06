package com.assignment6.spring.controllers;

import com.assignment6.spring.models.Post;
import com.assignment6.spring.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/posts")
    public class PostController {
        private final PostService postService;

        @PostMapping
        public ResponseEntity<Post> createPost(@RequestBody Post post,
                                               @RequestParam Long authorId) {
            return new ResponseEntity<>(postService.createPost(post, authorId), HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<Page<Post>> getPosts(
                @RequestParam(required = false) String author,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "5") int size) {
            return ResponseEntity.ok(postService.getPosts(author, page, size));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Post> getPost(@PathVariable Long id) {
            return ResponseEntity.ok(postService.getPost(id));
        }
    }