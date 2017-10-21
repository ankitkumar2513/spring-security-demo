package com.example.demo.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

}
