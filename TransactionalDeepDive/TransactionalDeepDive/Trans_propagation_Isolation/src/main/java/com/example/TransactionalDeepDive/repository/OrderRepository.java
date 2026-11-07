package com.example.TransactionalDeepDive.repository;

import com.example.TransactionalDeepDive.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<Order,Long> {
}
