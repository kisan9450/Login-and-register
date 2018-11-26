package com.stackroute.service;

import com.stackroute.domain.User;

public interface UserService {
    User save(User user);

    User findByUserName(String userName);
}
