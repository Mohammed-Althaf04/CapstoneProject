package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.ParkingSlot;
/**
 * Spring Data JPA repository interface providing database access methods for ParkingSlot entities.
 *
 * @author Naveen Muthu
 */

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

}