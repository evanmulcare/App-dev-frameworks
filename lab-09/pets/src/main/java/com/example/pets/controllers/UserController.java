package com.example.pets.controllers;

import com.example.pets.entities.User;
import com.example.pets.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/change-password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> editUserPassword(@PathVariable Long id, @RequestBody String newPassword) {
        User updatedUser = userService.editUserPassword(id, newPassword);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{id/change-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> editUserStatus(@PathVariable Long id, @RequestBody boolean unlocked) {
        User updatedUser = userService.editUserStatus(id, unlocked);
        return ResponseEntity.ok(updatedUser);
    }
}
