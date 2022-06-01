package com.example.projekt_k.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {

    private Long id;

    private String title;

    private String text;

    private Long userId;

    private UserDto userDto;

    private long likeCount;

    private boolean canRemove;

    private Long fileId;

    private String fileUrl;

}
