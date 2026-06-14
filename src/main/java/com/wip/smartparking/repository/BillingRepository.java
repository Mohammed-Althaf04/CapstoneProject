package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {

}