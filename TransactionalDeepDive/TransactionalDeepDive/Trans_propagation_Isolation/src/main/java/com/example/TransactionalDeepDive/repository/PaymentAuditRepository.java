package com.example.TransactionalDeepDive.repository;

import com.example.TransactionalDeepDive.model.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAuditRepository extends JpaRepository<PaymentAudit,Long> {
}
