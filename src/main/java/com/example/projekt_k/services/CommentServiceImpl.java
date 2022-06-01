package com.example.projekt_k.services;

import com.example.projekt_k.dto.CommentDto;
import com.example.projekt_k.dto.PostDto;
import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.entity.Post;
import com.example.projekt_k.entity.PostComment;
import com.example.projekt_k.entity.User;
import com.example.projekt_k.mappers.CommentMapper;
import com.example.projekt_k.mappers.PostsMapper;
import com.example.projekt_k.mappers.UserMapper;
import com.example.projekt_k.repository.CommentRepository;
import com.example.projekt_k.repository.PostRepository;
import com.example.projekt_k.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends AbstractService implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private CommentMapper СommentMapper;


    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository,
                              PostRepository postRepository) {
        super(userRepository);
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        PostComment postComment = new PostComment();
        UserDto userDto = getAuthUser();

        String text = commentDto.getText();
        Long postId = commentDto.getPostId();

        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userDto.getId());

        postComment.setText(text);
        postComment.setPost(post);
        postComment.setUser(user);
        PostComment save = commentRepository.save(postComment);

        CommentDto example = СommentMapper.toDto(save);

        commentDto.setUserDto(userDto);
        commentDto.setId(save.getId());
        return commentDto;
    }

    @Override
    public List<CommentDto> getAllByPostId(@NonNull Long postId) {
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(post.getUserId());
        PostDto postDto = PostsMapper.toDto(post, UserMapper.toDto(user));
        return commentRepository.findAllByPost_Id(postId)
                .stream()
                .map(postComment -> getCommentDto(postId, postDto, postComment))
                .collect(Collectors.toList());
    }

    private CommentDto getCommentDto(Long postId, PostDto postDto, PostComment postComment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(postComment.getId());
        commentDto.setPostDto(postDto);
        commentDto.setText(postComment.getText());
        commentDto.setPostId(postId);
        User commentUSer = postComment.getUser();
        commentDto.setUserDto(UserMapper.toDto(commentUSer));
        return commentDto;
    }
}