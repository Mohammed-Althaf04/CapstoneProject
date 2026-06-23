package com.wip.smartparking.dto.request;

import com.wip.smartparking.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/**
 * Data Transfer Object (DTO) representing a request body for User creation or updates.
 *
 * @author Naveen Muthu
 */

public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$",
             message = "Phone number must contain exactly 10 digits")
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 6,
          message = "Password must contain at least 6 characters")
    private String password;

    @NotNull(message = "User role is required")
    private UserRole role;
    
	public UserRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserRequestDTO(String name,String email,String phone,String password,UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRequestDTO [name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ ", role=" + role + "]";
	}
    
}