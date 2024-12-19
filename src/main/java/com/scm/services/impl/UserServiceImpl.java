package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.exceptions.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user1 = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());
        user1.setPassword(user.getPassword());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setProfilePic(user.getProfilePic());
        user1.setEnabled(user.isEnabled());
        user1.setEmailVerified(user.isEmailVerified());
        user1.setPhoneVerified(user.isPhoneVerified());
        user1.setProvider(user.getProvider());
        user1.setProviderId(user.getProviderId());
        User saveUser = userRepo.save(user1);

        return Optional.ofNullable(saveUser);
    }

    @Override
    public void deleteUser(int id) {
        User user1 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userRepo.delete(user1);
    }

    @Override
    public boolean isUserExits(int userId) {
        User user1 = userRepo.findById(userId).orElse(null);

        return user1 != null ? true : false;
    }

    @Override
    public boolean isUserExitsByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);

        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
