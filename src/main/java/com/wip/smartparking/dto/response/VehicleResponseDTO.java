package com.wip.smartparking.dto.response;

import com.wip.smartparking.enums.VehicleType;

public class VehicleResponseDTO {

    private Long vehicleId;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Long userId;
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

    // Generate Getters & Setters
    
    
}