package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.ParkingRecord;
/**
 * Spring Data JPA repository interface providing database access methods for ParkingRecord entities.
 *
 * @author Naveen Muthu
 */

public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

}