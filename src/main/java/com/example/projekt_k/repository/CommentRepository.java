package com.example.projekt_k.repository;

import com.example.projekt_k.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findAllByPost_Id(Long postId);

}
