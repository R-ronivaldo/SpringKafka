package com.springkafka.springkafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
}