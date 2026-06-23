package com.wip.smartparking.dto.request;

import jakarta.validation.constraints.NotBlank;
/**
 * Data Transfer Object (DTO) representing a request body for Login creation or updates.
 *
 * @author Naveen Muthu
 */

public class LoginRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    public LoginRequest() {
        super();
    }

    public LoginRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest [username=" + username + "]";
    }
}
