package com.example.emt_lab.service.Impl;

import com.example.emt_lab.exceptions.InvalidArgumentsException;
import com.example.emt_lab.exceptions.InvalidUserCredentialsException;
import com.example.emt_lab.model.User;
import com.example.emt_lab.repository.UserRepository;
import com.example.emt_lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
