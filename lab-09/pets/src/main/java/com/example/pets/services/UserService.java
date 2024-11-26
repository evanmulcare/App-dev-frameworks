package com.example.pets.services;
import com.example.pets.entities.User;

public interface UserService {
    User createUser(User user);
    User editUserPassword(Long id, String newPassword);
    User editUserStatus(Long id, boolean unlocked);
    void deleteUser(Long id);
}
