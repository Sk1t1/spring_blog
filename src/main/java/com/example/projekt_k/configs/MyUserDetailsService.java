package com.example.projekt_k.configs;

import com.example.projekt_k.entity.User;
import com.example.projekt_k.entity.UserRole;
import com.example.projekt_k.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
    public class MyUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        @Transactional
        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            User user = userRepository.findByEmail(userName);
            return getByUser(user);
        }

        private Principle getByUser(User user) {
            Set<UserRole> roles = user.getRoles();
            return new Principle(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), roles);
        }
    }
