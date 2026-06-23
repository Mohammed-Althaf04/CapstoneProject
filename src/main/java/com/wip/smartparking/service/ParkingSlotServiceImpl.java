package com.wip.smartparking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.smartparking.entity.ParkingSlot;
import com.wip.smartparking.enums.SlotStatus;
import com.wip.smartparking.exception.ParkingSlotException;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.ParkingSlotRepository;
/**
 * Service implementation class containing concrete business logic and transactional operations for ParkingSlot.
 *
 * @author Naveen Muthu
 */

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Override
    public ParkingSlot saveSlot(ParkingSlot slot) {
        return parkingSlotRepository.save(slot);
    }

    @Override
    public ParkingSlot getSlotById(Long id) {
        return parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Parking Slot not found with id : " + id));
    }

    @Override
    public List<ParkingSlot> getAllSlots() {
        return parkingSlotRepository.findAll();
    }

    @Override
    public void deleteSlot(Long id) {

        ParkingSlot slot = parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Slot not found"));

        if(slot.getStatus() == SlotStatus.OCCUPIED) {
            throw new ParkingSlotException(
                    "Cannot delete occupied slot");
        }

        parkingSlotRepository.delete(slot);
    }
}