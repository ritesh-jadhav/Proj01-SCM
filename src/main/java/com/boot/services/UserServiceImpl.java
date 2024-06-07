package com.boot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.entities.User;
import com.boot.helpers.AppConstanct;
import com.boot.helpers.ResourceNotFoundException;
import com.boot.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        // password encoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // set user role

        user.setRoleList(List.of(AppConstanct.ROLE_USER));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserByUserId(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User existingUser = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setAbout(user.getAbout());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setProfilePic(user.getProfilePic());
        existingUser.setProvider(user.getProvider());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setPhoneVerified(user.isPhoneVerified());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setProviderId(user.getProviderId());

        User savedUser = userRepo.save(existingUser);

        return Optional.ofNullable(savedUser);

    }

    @Override
    public void deleteUser(String userId) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(existingUser);
    }

    @Override
    public boolean isUserExist(String userId) {
        User existingUser = userRepo.findById(userId)
                .orElse(null);
        return existingUser != null ? true : false;

    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
