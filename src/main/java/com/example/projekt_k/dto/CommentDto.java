package com.example.projekt_k.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "userDto")
@ToString
public class CommentDto {
    private Long id;
    private Long postId;
    private String text;
    private UserDto userDto;
    private PostDto postDto;
}
