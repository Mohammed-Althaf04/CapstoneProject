package com.wip.smartparking.entity;

import com.wip.smartparking.enums.BillingStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "billing")
public class Billing {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private Double amount;

    private Double tax;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;

    @OneToOne
    @JoinColumn(name = "record_id")
    private ParkingRecord parkingRecord;

    public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BillingStatus getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(BillingStatus billingStatus) {
		this.billingStatus = billingStatus;
	}

	public ParkingRecord getParkingRecord() {
		return parkingRecord;
	}

	public void setParkingRecord(ParkingRecord parkingRecord) {
		this.parkingRecord = parkingRecord;
	}

	
}