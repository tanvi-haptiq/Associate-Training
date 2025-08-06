package com.assignment6.spring.models;

//package com.example.blog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();
}
