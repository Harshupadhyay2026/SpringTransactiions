package com.example.TransactionalDeepDive.service;

import com.example.TransactionalDeepDive.model.Order;
import com.example.TransactionalDeepDive.repository.OrderRepository;
import com.example.TransactionalDeepDive.repository.PaymentAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private PaymentAuditService paymentAuditService;

    public OrderService(OrderRepository orderRepository, PaymentAuditService paymentAuditService) {
        this.orderRepository = orderRepository;
        this.paymentAuditService = paymentAuditService;
    }

    @Transactional
    public void placeOrder(Order order){
        orderRepository.save(order);

           try{
               paymentAuditService.audit(order);
           }
           catch (Exception e){}




        //transactional propagates to all class coming inside blocks of transactional
    }
}
