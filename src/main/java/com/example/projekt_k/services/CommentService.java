package com.example.projekt_k.services;

import com.example.projekt_k.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto);

    List<CommentDto> getAllByPostId(Long postId);

}
