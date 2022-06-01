package com.example.projekt_k.mappers;

import com.example.projekt_k.dto.CommentDto;
import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.entity.PostComment;
import com.example.projekt_k.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(target="userDto",expression = "java(toUserDto(postComment.getUser()))"),
    })
    CommentDto toDto(PostComment postComment);


    @Mappings({
            @Mapping(target="password", ignore = true),
    })
    UserDto toUserDto(User user);

}