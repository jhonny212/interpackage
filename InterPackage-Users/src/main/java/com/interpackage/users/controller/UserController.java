package com.interpackage.users.controller;

import com.interpackage.users.util.CommonParams;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.interpackage.users.model.User;
import com.interpackage.users.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping ("/api/users/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<Page<User>> getAllUsers(CommonParams commonParams) {
        return ResponseEntity.ok(userService.getAllUser(commonParams.getPage(),commonParams.getMax(), commonParams.isPagination()));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody User usr){
        User user = null;
        try {
            user = userService.createUser(usr);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(user);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateUser(@RequestBody User usr, @PathVariable String name){
        User user = null;
        try {
            user = userService.updateUser(usr,name);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(user);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteUser(@PathVariable String name){
        try {
            userService.deleteUser(name);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().build();
    }
}
