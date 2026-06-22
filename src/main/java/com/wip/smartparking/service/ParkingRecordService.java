package com.wip.smartparking.service;

import java.util.List;

import com.wip.smartparking.entity.ParkingRecord;

public interface ParkingRecordService {

    ParkingRecord createEntry(ParkingRecord record, Long vehicleId, Long slotId);
    ParkingRecord exitVehicle(Long recordId);
    ParkingRecord getRecordById(Long id);
    List<ParkingRecord> getAllRecords();
    void deleteRecord(Long id);
}