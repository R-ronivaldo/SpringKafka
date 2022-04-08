package com.springkafka.springkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import com.springkafka.springkafka.model.User;
import com.springkafka.springkafka.producer.UserProducer;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserProducer userProducer;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userRequest){
        userRequest.setId(UUID.randomUUID().toString());
        
        // TODO - PasswordEncoder before send User to DB.

        // TODO - save User in DB. If completed, continue.

        // TODO - remove password of the User before send message to kafka.
        
        userProducer.send(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest);
    }

}