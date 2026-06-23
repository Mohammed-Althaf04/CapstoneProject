package com.wip.smartparking.service;

import java.util.List;
import com.wip.smartparking.entity.ParkingSlot;
/**
 * Service interface defining the business contract and operations for ParkingSlot management.
 *
 * @author Naveen Muthu
 */

public interface ParkingSlotService {

    ParkingSlot saveSlot(ParkingSlot slot);
    List<ParkingSlot> getAllSlots();
    ParkingSlot getSlotById(Long id);
    void deleteSlot(Long id);
}