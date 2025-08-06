package com.assignment6.spring.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String title;
    private String content;
    private Long authorId;
}