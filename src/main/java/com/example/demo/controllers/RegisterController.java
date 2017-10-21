package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.models.RegisterRequest;
import com.example.demo.models.RegisterResponse;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping
    public RegisterResponse register(@RequestBody RegisterRequest request) throws Exception {
        if(!validateRegisterRequest(request)) {
            throw new Exception("invalid request");
        }

        try {
            userRepository.insert(User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .password(request.getPassword())
                    .userName(request.getUserName())
                    .rolesList(Collections.singletonList("ROLE_USER"))
                    .build());
            return RegisterResponse.builder().message("success").userName(request.getUserName())
                    .build();
        } catch (Exception e) {
            return RegisterResponse.builder().message("duplicate username").userName(null)
                    .build();
        }
    }

    private boolean validateRegisterRequest(final RegisterRequest request) {
        return !(request.getFirstName().isEmpty() ||
                request.getLastName().isEmpty() ||
                request.getUserName().isEmpty() ||
                request.getPassword().isEmpty());
    }
}
