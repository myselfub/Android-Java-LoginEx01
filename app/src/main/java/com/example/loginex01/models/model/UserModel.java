package com.example.loginex01.models.model;

import java.util.Map;

import lombok.Data;

@Data
public class UserModel {
    private String username;
    private String email;
    private String password;
    private String password1;
    private String password2;
    private String non_field_errors;
    private String access_token;
    private String refresh_token;
    private boolean legacy;
    private Map<String, String> user;
}