package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.ParkingRecord;

public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

}