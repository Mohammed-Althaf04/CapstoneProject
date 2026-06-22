package com.wip.smartparking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.smartparking.entity.Billing;
import com.wip.smartparking.entity.ParkingRecord;
import com.wip.smartparking.enums.BillingStatus;
import com.wip.smartparking.exception.PaymentFailureException;
import com.wip.smartparking.exception.ResourceNotFoundException;
import com.wip.smartparking.repository.BillingRepository;
import com.wip.smartparking.repository.ParkingRecordRepository;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ParkingRecordRepository parkingRecordRepository;

    @Override
    public Billing generateBill(Long recordId) {

        // ✅ prevent duplicate billing
        Optional<Billing> existing =
                billingRepository.findByParkingRecord_RecordId(recordId);

        if (existing.isPresent()) {
            return existing.get();
        }

        ParkingRecord record = parkingRecordRepository.findById(recordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Record not found"));

        if (record.getExitTime() == null) {
            throw new PaymentFailureException("Vehicle has not exited yet");
        }

        long minutes = java.time.Duration
                .between(record.getEntryTime(), record.getExitTime())
                .toMinutes();

        double hours = Math.max(1, Math.ceil(minutes / 60.0));
        double rate = 20.0;

        double amount = hours * rate;
        double tax = amount * 0.10;
        double total = amount + tax;

        Billing bill = new Billing();
        bill.setParkingRecord(record);
        bill.setAmount(amount);
        bill.setTax(tax);
        bill.setTotalAmount(total);
        bill.setBillingStatus(BillingStatus.PENDING);

        return billingRepository.save(bill);
    }

    @Override
    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    @Override
    public Billing getBillById(Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bill not found"));
    }

    @Override
    public void deleteBill(Long id) {
        billingRepository.deleteById(id);
    }
    
    @Override
    public Billing saveBill(Billing bill) {
        return billingRepository.save(bill);
    }
}