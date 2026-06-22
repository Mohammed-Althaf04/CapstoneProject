package com.wip.smartparking.dto.response;

import com.wip.smartparking.enums.UserRole;

public class UserResponseDTO {

    private Long userId;
    private String name;
    private String email;
    private String phone;
    private UserRole role;
    
	public UserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserResponseDTO(Long userId, String name, String email, String phone, UserRole role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserResponseDTO [userId=" + userId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + "]";
	}
	
}