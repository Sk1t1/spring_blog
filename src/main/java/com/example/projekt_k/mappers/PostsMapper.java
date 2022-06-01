package com.example.projekt_k.mappers;

import com.example.projekt_k.dto.PostDto;
import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.entity.Post;

public class PostsMapper {

    public static PostDto toDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .text(post.getText())
                .title(post.getTitle())
                .userId(post.getUserId())
                .build();
    }

    public static PostDto toDto(Post post, UserDto userDto) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setText(post.getText());
        postDto.setTitle(post.getTitle());
        postDto.setUserId(post.getUserId());
        postDto.setUserDto(userDto);
        return postDto;
    }

    public static Post toEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setUserId(postDto.getUserId());
        return post;
    }

}