package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

}