package com.example.demo.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResponse {

    private String message;
    private String userName;
}
