package com.wip.smartparking.dto.response;

import com.wip.smartparking.enums.BillingStatus;
/**
 * Data Transfer Object (DTO) representing a serialized response payload for Billing data.
 *
 * @author Naveen Muthu
 */

public class BillingResponseDTO {

    private Long billId;
    private Double amount;
    private Double tax;
    private Double totalAmount;
    private BillingStatus billingStatus;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    public BillingResponseDTO() {
		super();
	}
    
	public BillingResponseDTO(Long billId, Double amount, Double tax, Double totalAmount, BillingStatus billingStatus) {
		super();
		this.billId = billId;
		this.amount = amount;
		this.tax = tax;
		this.totalAmount = totalAmount;
		this.billingStatus = billingStatus;
	}
	
	public BillingResponseDTO(Long billId, Double amount, Double tax, Double totalAmount, BillingStatus billingStatus, String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
		super();
		this.billId = billId;
		this.amount = amount;
		this.tax = tax;
		this.totalAmount = totalAmount;
		this.billingStatus = billingStatus;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.razorpaySignature = razorpaySignature;
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

	@Override
	public String toString() {
		return "BillingResponseDTO [billId=" + billId + ", amount=" + amount + ", tax=" + tax + ", totalAmount="
				+ totalAmount + ", billingStatus=" + billingStatus + ", razorpayOrderId=" + razorpayOrderId
				+ ", razorpayPaymentId=" + razorpayPaymentId + ", razorpaySignature=" + razorpaySignature + "]";
	}
    
}