package com.interpackage.users.controller;

import com.interpackage.basedomains.dto.User;
import com.interpackage.basedomains.dto.UserEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interpackage.users.producers.UserProducer;

@RestController
@RequestMapping("/api/v1")
public class UserKafkaController {
    
    private UserProducer userProducer;

    public UserKafkaController(UserProducer userProducer) {
        this.userProducer = userProducer;
    }

    @PostMapping("/users")
    public String TestUsers(@RequestBody User user){
        UserEvent userEvent = new UserEvent();
        userEvent.setMessage("Prueba");
        userEvent.setStatus("PENDING");
        userEvent.setUser(user);
        userProducer.SendMessage(userEvent);
        return "User placed";
    }
    
}
