package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(int id);

    Optional<User> updateUser(User user);

    void deleteUser(int id);

    boolean isUserExits(int userId);

    boolean isUserExitsByEmail(String email);

    List<User> getAllUsers();

    boolean isUserExistsByEmail(String email);

    User getUserByEmail(String email);
}
