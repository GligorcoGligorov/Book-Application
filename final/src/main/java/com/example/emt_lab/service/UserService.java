package com.example.emt_lab.service;

import com.example.emt_lab.enumerations.Role;
import com.example.emt_lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
