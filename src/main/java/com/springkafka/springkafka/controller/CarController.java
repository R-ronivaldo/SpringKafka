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
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private UserProducer userProducer;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userRequest){
        User user = User.builder().id(UUID.randomUUID().toString()).username(userRequest.getUsername()).email(userRequest.getEmail()).build();
        userProducer.send(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}