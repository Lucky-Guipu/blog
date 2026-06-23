package com.evan.service;

import com.evan.entity.User;

public interface UserService {
    User findUserByUsernameAndPassword(String username, String password);

    User findUserById(Long id);

    boolean changeAccount(User user, String jwt);
}

