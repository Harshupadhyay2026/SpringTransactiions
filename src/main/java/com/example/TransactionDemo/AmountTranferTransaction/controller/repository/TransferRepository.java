package com.example.TransactionDemo.AmountTranferTransaction.controller.repository;

import com.example.TransactionDemo.AmountTranferTransaction.controller.model.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferRecord,Long> {
}
