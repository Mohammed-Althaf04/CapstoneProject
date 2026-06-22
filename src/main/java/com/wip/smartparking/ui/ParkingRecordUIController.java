package com.wip.smartparking.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wip.smartparking.entity.ParkingRecord;
import com.wip.smartparking.service.ParkingRecordService;
import com.wip.smartparking.service.ParkingSlotService;
import com.wip.smartparking.service.UserService;
import com.wip.smartparking.service.VehicleService;

@Controller
@RequestMapping("/ui/records")
public class ParkingRecordUIController {

    @Autowired
    private ParkingRecordService parkingRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ParkingSlotService parkingSlotService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", parkingRecordService.getAllRecords());
        return "parkingrecord/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("record", new ParkingRecord());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("slots", parkingSlotService.getAllSlots().stream()
                .filter(s -> s.getStatus() != com.wip.smartparking.enums.SlotStatus.OCCUPIED)
                .toList());
        return "parkingrecord/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ParkingRecord record,
                       @RequestParam Long vehicleId,
                       @RequestParam Long slotId) {

        parkingRecordService.createEntry(record, vehicleId, slotId);
        return "redirect:/ui/records";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        parkingRecordService.deleteRecord(id);
        return "redirect:/ui/records";
    }
    
    @GetMapping("/exit/{id}")
    public String exit(@PathVariable Long id) {
        parkingRecordService.exitVehicle(id);
        return "redirect:/ui/records";
    }
}