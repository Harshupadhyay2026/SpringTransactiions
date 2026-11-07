package com.example.TransactionalDeepDive.service;

import com.example.TransactionalDeepDive.model.Order;
import com.example.TransactionalDeepDive.model.PaymentAudit;
import com.example.TransactionalDeepDive.repository.PaymentAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentAuditService {
    private PaymentAuditRepository paymentAuditRepository;

    public PaymentAuditService(PaymentAuditRepository paymentAuditRepository) {
        this.paymentAuditRepository = paymentAuditRepository;
    }

//
//    @Transactional(
//            propagation = Propagation.REQUIRED)  ---> there should be no error in any methodes inisde transaction other wise it will roll backs.Its always depends o parent class....
//
//    @Transactional(
//            propagation = Propagation.REQUIRES_NEW) ---> if transaction exist at parent class it suspends it and creates new transaction for its own which is independent means if parent's transaction roll back its does not mean it will roll back also

//
//@Transactional(
//        propagation = Propagation.SUPPORTS) ---->>if transaction exist it joins it (act like requred) if transaction not exist it creates its own(act like requires new)
//
//    @Transactional(
//            propagation = Propagation.MANDATORY) -->if transactions exist it joins it (likes required) if not it throw exception
//
//    @Transactional(
//            propagation = Propagation.NOT_SUPPORTED)--> if transactions exist already it  suspends it and run without transaction

//    @Transactional(
//            propagation = Propagation.NEVER) -->>> if transaction exist it will throw exception always



    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ
    )

     public void audit(Order order){

         PaymentAudit paymentAudit = new PaymentAudit(
                 order.getAmount(),
                 order.getId(),
                 true);

         paymentAuditRepository.save(paymentAudit);

    throw new RuntimeException("some error occured");

       //  throw new RuntimeException("some error occured");

//  isolation -->>> it is used to reduce inconsistency in data read
// inconsistency like-->> ||  dirty readiing( > non-repeatable read >,phantom read (decreasing level of inconsistency)
//
//        Isolation.READ_UNCOMMITTED -->>>it cant remove any type  inconsistency reading.

//         Isolation.READ_COMMITTED,--->>>used to remove only dirty reading  (prefered)

//         Isolation.REPEATABLE_READ,-->>USED TO REMOVE dirty reading as well as non -repeatable read  (most prefered)

//          Isolation.SERIALIZABLE -->>>used to remove all three type of inconsistency
//


}
}
