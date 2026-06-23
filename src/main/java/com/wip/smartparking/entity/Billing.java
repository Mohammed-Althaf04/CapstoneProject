package com.wip.smartparking.entity;

import com.wip.smartparking.enums.BillingStatus;
import jakarta.persistence.*;
/**
 * Entity model representing a persistent Billing database table mapped via JPA.
 *
 * @author Naveen Muthu
 */

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

    @OneToOne(optional = false)
    @JoinColumn(name = "record_id", nullable = false, unique = true)
    private ParkingRecord parkingRecord;

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    public Billing() {}

    public Billing(Long billId, Double amount, Double tax,
                   Double totalAmount, BillingStatus billingStatus,
                   ParkingRecord parkingRecord) {
        this.billId = billId;
        this.amount = amount;
        this.tax = tax;
        this.totalAmount = totalAmount;
        this.billingStatus = billingStatus;
        this.parkingRecord = parkingRecord;
    }

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

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public String getRazorpaySignature() {
		return razorpaySignature;
	}

	public void setRazorpaySignature(String razorpaySignature) {
		this.razorpaySignature = razorpaySignature;
	}
}