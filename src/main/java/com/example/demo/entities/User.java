package com.example.demo.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@ToString
@Getter
@Builder
public class User {

    @Id
    private String userName;

    private String firstName;
    private String lastName;
    private String password;
    private List<String> rolesList;
}
