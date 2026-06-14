package com.wip.smartparking.mapper;

import com.wip.smartparking.dto.request.BillingRequestDTO;
import com.wip.smartparking.dto.response.BillingResponseDTO;
import com.wip.smartparking.entity.Billing;

public class BillingMapper {

    public static Billing toEntity(BillingRequestDTO dto) {

        Billing bill = new Billing();

        bill.setAmount(dto.getAmount());
        bill.setTax(dto.getTax());
        bill.setTotalAmount(dto.getTotalAmount());
        bill.setBillingStatus(dto.getBillingStatus());

        return bill;
    }

    public static BillingResponseDTO toResponseDTO(Billing billing) {

        BillingResponseDTO dto = new BillingResponseDTO();

        dto.setBillId(billing.getBillId());
        dto.setAmount(billing.getAmount());
        dto.setTax(billing.getTax());
        dto.setTotalAmount(billing.getTotalAmount());
        dto.setBillingStatus(billing.getBillingStatus());

        return dto;
    }
}