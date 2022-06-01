package com.example.projekt_k.entity;

import javax.persistence.*;

@Entity
@Table(name = "post_files")
public class PostFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    @ManyToOne
    @JoinColumn(name = "files_id", referencedColumnName = "id")
    private FilesEntity file;

    public PostFiles() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public FilesEntity getFile() {
        return file;
    }

    public void setFile(FilesEntity file) {
        this.file = file;
    }
}
