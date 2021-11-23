package ru.red.otussimplerestfulcrud.dto;

import lombok.Data;

@Data
public class UserPostDto {
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
