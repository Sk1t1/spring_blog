package com.example.projekt_k.services;

import com.example.projekt_k.dto.SearchRequest;
import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.entity.User;
import com.example.projekt_k.entity.UserRole;
import com.example.projekt_k.enums.Roles;
import com.example.projekt_k.exeptions.BadRequestException;
import com.example.projekt_k.mappers.UserMapper;
import com.example.projekt_k.repository.UserRepository;
import com.example.projekt_k.repository.specification.UserSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class UserServiceImpl extends AbstractService implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDto findUserByEmail(SearchRequest searchRequest) {

        if (Objects.isNull(searchRequest)) {
            throw new BadRequestException("search object cant be null");
        }

        if (Objects.isNull(searchRequest.getEmail()) || searchRequest.getEmail().length() < 1) {
            throw new BadRequestException("email cant be null or empty");
        }

        User byEmail = userRepository.findByEmail(searchRequest.getEmail());

        return UserMapper.toDto(byEmail);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new BadRequestException("email already exists");
        }
        if (userDto.getAge() < 18) {
            throw new BadRequestException("age cant be <18");
        }
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder().encode(userDto.getPassword()));
        String activationCode = generateString();

        user.setActivationCode(activationCode);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(Roles.ROLE_USER, user));
        user.setRoles(userRoles);
        User save = userRepository.save(user);
//        mailSe.sendEmail(activationCode);
        userDto = UserMapper.toDto(save);
        return userDto;
    }

    private String generateString(){
        String generatedString = new Random().toString();
        return generatedString;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return UserMapper.toDto(userRepository.findById(id).
                orElseThrow(() -> new BadRequestException("User not found")));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDto getCurrentUser() {
        UserDto authUser = getAuthUser();
        return authUser;
    }

    @Override
    public List<UserDto> searchUsers(UserFilter userFilter) {
        return userRepository.findAll(new UserSpecification(userFilter))
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (Objects.nonNull(user)) {
            user.setActivated(true);
            user.setActivationCode(null);
            userRepository.save(user);
        }
    }
}
