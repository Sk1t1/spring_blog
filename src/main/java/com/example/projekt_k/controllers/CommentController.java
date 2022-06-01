package com.example.projekt_k.controllers;

import com.example.projekt_k.dto.CommentDto;
import com.example.projekt_k.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/comment")
    public ResponseEntity<CommentDto> addCommentToPost(@RequestBody CommentDto commentDto) {
        CommentDto comment = commentService.createComment(commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


    @GetMapping(value = "/comments/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getAllByPostId(postId), HttpStatus.OK);
    }

}
