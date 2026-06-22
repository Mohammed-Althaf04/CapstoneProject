package com.wip.smartparking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    Optional<Billing> findByParkingRecord_RecordId(Long recordId);

    void deleteByParkingRecord_RecordId(Long recordId);
}