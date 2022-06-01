package com.example.projekt_k.repository;

import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.entity.User;
import com.example.projekt_k.mappers.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import javax.persistence.EntityManager;

@Repository
public class UserDao {
    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        entityManager.persist(user);
    }

    public UserDto getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return UserMapper.toDto(user);
    }
}
