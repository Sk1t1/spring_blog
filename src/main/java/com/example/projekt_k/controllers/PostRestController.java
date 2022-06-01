package com.example.projekt_k.controllers;

import com.example.projekt_k.dto.PostDto;
import com.example.projekt_k.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService= postService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto byId = postService.getById(postId);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.create(postDto), HttpStatus.OK);
    }

}
