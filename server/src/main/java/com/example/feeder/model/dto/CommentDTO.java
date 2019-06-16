package com.example.feeder.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class CommentDTO {

    private Long id;
    private String content;
    @JsonBackReference
    private PostDTO post;

    public CommentDTO() {
    }

    public CommentDTO(String content) {
        this.content = content;
    }

    public CommentDTO(String content, PostDTO post) {
        this.content = content;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

}
