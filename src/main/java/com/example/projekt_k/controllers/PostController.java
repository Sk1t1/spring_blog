package com.example.projekt_k.controllers;

import com.example.projekt_k.dto.PostDto;
import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/addPost")
    public String postsPage() {
        return "addPost";
    }

//    @PostMapping("/posts")
//    public String createPost(Model model, @ModelAttribute("post") PostDto postDto) {
//        postDto = postService.create(postDto);
//        return "home";
//
//    }

    @GetMapping("/posts")
    public String postsList(Model model) {
        List<PostDto> allPosts = postService.getAllPosts();
        model.addAttribute("posts", allPosts);
        return "posts";
    }


    @GetMapping("/like/{postId}")
    public String postsList(@PathVariable Long postId) {
        postService.doLikeOperation(postId);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{postId}")
    public String delete(@PathVariable Long postId) {
        try {
            postService.delete(postId);
        } catch (AccessDeniedException e) {
            System.out.println("AccessDeniedException");
        }
        return "redirect:/posts";
    }

    @GetMapping("/postPage/{postId}")
    public String goToPostPage(@PathVariable Long postId) {

//        PostDto post = postService.getById(postId);
//
//        model.addAttribute("post", post);
        return "postPage";
    }
}