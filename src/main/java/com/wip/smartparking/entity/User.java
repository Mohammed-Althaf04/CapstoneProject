package com.wip.smartparking.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wip.smartparking.enums.UserRole;

import jakarta.persistence.*;
/**
 * Entity model representing a persistent User database table mapped via JPA.
 *
 * @author Naveen Muthu
 */

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Reservation> reservations;
    
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public User(Long userId, String name, String email, String phone, String password, UserRole role,
			List<Vehicle> vehicles, List<Reservation> reservations) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.vehicles = vehicles;
		this.reservations = reservations;
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

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", role=" + role + ", vehicles=" + vehicles + ", reservations=" + reservations + "]";
	}
	
}