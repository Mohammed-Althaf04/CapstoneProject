package com.wip.smartparking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wip.smartparking.entity.ParkingRecord;
import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.entity.Vehicle;
import com.wip.smartparking.enums.SlotStatus;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.BillingRepository;
import com.wip.smartparking.repository.ParkingRecordRepository;
import com.wip.smartparking.repository.ParkingSlotRepository;
import com.wip.smartparking.repository.VehicleRepository;

@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {

    @Autowired
    private ParkingRecordRepository parkingRecordRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    
    @Autowired
    private BillingRepository billingRepository;
    
    @Autowired
    private BillingService billingService;

    @Override
    public ParkingRecord createEntry(ParkingRecord record, Long vehicleId, Long slotId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        ParkingSlot slot = parkingSlotRepository.findById(slotId)
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found"));
        if (slot.getStatus() == SlotStatus.OCCUPIED) {
            throw new RuntimeException("Slot not available");
        }

        record.setVehicle(vehicle);
        record.setParkingSlot(slot);
        record.setEntryTime(LocalDateTime.now());

        slot.setStatus(SlotStatus.OCCUPIED);
        parkingSlotRepository.save(slot);

        return parkingRecordRepository.save(record);
    }

    @Override
    public ParkingRecord exitVehicle(Long recordId) {

        ParkingRecord record = parkingRecordRepository.findById(recordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Record not found"));

        record.setExitTime(LocalDateTime.now());

        double duration = Duration.between(
                record.getEntryTime(),
                record.getExitTime())
                .toMinutes();

        if(duration <= 0) {
            duration = 1.0;
        }

        record.setDuration(duration);

        ParkingSlot slot = record.getParkingSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        parkingSlotRepository.save(slot);

        ParkingRecord savedRecord = parkingRecordRepository.save(record);
        billingService.generateBill(savedRecord.getRecordId());
        return savedRecord;
    }

    @Override
    public ParkingRecord getRecordById(Long id) {
        return parkingRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<ParkingRecord> getAllRecords() {
        return parkingRecordRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteRecord(Long id) {

        billingRepository.deleteByParkingRecord_RecordId(id);

        parkingRecordRepository.deleteById(id);
    }
}