package com.example.projekt_k.repository;

import com.example.projekt_k.entity.PostFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostFilesRepository extends JpaRepository<PostFiles, Long> {

    List<PostFiles> findAllByPostId(Long postId);
}