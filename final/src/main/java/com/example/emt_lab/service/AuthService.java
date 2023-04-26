package com.example.emt_lab.service;

import com.example.emt_lab.model.User;

public interface AuthService {

    User login(String username, String password);
}
