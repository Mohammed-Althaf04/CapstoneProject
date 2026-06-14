package com.wip.smartparking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.smartparking.entity.ParkingRecord;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.ParkingRecordRepository;

@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {

    @Autowired
    private ParkingRecordRepository parkingRecordRepository;

    @Override
    public ParkingRecord saveRecord(ParkingRecord record) {
        return parkingRecordRepository.save(record);
    }

    @Override
    public ParkingRecord getRecordById(Long id) {
        return parkingRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Parking Record not found with id : " + id));
    }

    @Override
    public List<ParkingRecord> getAllRecords() {
        return parkingRecordRepository.findAll();
    }

    @Override
    public void deleteRecord(Long id) {

        ParkingRecord record = parkingRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Parking Record not found with id : " + id));

        parkingRecordRepository.delete(record);
    }
}