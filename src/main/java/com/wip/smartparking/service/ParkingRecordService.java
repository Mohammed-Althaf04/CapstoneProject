package com.wip.smartparking.service;

import java.util.List;

import com.wip.smartparking.entity.ParkingRecord;

public interface ParkingRecordService {

    ParkingRecord saveRecord(ParkingRecord record);

    ParkingRecord getRecordById(Long id);

    List<ParkingRecord> getAllRecords();

    void deleteRecord(Long id);
}