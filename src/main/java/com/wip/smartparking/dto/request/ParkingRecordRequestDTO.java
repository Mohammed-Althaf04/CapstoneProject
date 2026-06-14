package com.wip.smartparking.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class ParkingRecordRequestDTO {

    @NotNull(message = "Entry time is required")
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private Double duration;

    @NotNull(message = "Vehicle Id is required")
    private Long vehicleId;

    @NotNull(message = "Slot Id is required")
    private Long slotId;

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }
}