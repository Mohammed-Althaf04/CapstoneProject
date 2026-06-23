package com.wip.smartparking.dto.response;

import com.wip.smartparking.enums.VehicleType;
/**
 * Data Transfer Object (DTO) representing a serialized response payload for Vehicle data.
 *
 * @author Naveen Muthu
 */

public class VehicleResponseDTO {

    private Long vehicleId;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Long userId;
    
	public VehicleResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VehicleResponseDTO(Long vehicleId, String vehicleNumber, VehicleType vehicleType, Long userId) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.userId = userId;
	}

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

	@Override
	public String toString() {
		return "VehicleResponseDTO [vehicleId=" + vehicleId + ", vehicleNumber=" + vehicleNumber + ", vehicleType="
				+ vehicleType + ", userId=" + userId + "]";
	}
    
}