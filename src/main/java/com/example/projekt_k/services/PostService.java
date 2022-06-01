package com.example.projekt_k.services;

import com.example.projekt_k.dto.PostDto;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface PostService {

    PostDto create(PostDto postDto);

    List<PostDto> getAllPosts();

    void doLikeOperation(long postId);

    void delete(long postId) throws AccessDeniedException;

    PostDto getById(Long postId);
}
