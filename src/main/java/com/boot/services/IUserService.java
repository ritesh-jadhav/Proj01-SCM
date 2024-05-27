package com.boot.services;

import java.util.List;
import java.util.Optional;

import com.boot.entities.User;

public interface IUserService {

    User saveUser(User user);
    Optional<User> getUserByUserId(String userId);
    Optional<User> updateUser(User user);
    void deleteUser(String userId);
    boolean isUserExist(String userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
}
