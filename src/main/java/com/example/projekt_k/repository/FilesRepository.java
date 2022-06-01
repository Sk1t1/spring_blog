package com.example.projekt_k.repository;

import com.example.projekt_k.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Long> {
}