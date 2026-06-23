package com.wip.smartparking.dto.request;

import com.wip.smartparking.enums.BillingStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
/**
 * Data Transfer Object (DTO) representing a request body for Billing creation or updates.
 *
 * @author Naveen Muthu
 */

public class BillingRequestDTO {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Tax is required")
    private Double tax;

    @NotNull(message = "Total amount is required")
    private Double totalAmount;

    @NotNull(message = "Billing status is required")
    private BillingStatus billingStatus;

    public BillingRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillingRequestDTO(Double amount, Double tax, Double totalAmount, BillingStatus billingStatus) {
		super();
		this.amount = amount;
		this.tax = tax;
		this.totalAmount = totalAmount;
		this.billingStatus = billingStatus;
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

	@Override
	public String toString() {
		return "BillingRequestDTO [amount=" + amount + ", tax=" + tax + ", totalAmount=" + totalAmount
				+ ", billingStatus=" + billingStatus + "]";
	}
    
    
}