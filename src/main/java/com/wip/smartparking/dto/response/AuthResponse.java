package com.wip.smartparking.dto.response;
/**
 * Data Transfer Object (DTO) representing a serialized response payload for Auth data.
 *
 * @author althaf
 */

public class AuthResponse {

    private String token;
    private String type = "Bearer";
    private String email;
    private String role;

    public AuthResponse() {
        super();
    }

    public AuthResponse(String token, String email, String role) {
        super();
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthResponse [email=" + email + ", role=" + role + "]";
    }
}
