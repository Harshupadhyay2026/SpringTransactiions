package com.example.TransactionDemo.AmountTranferTransaction.controller.service;

import com.example.TransactionDemo.AmountTranferTransaction.controller.model.Account;
import com.example.TransactionDemo.AmountTranferTransaction.controller.model.TransferRecord;
import com.example.TransactionDemo.AmountTranferTransaction.controller.repository.AccountRepository;
import com.example.TransactionDemo.AmountTranferTransaction.controller.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {
    private AccountRepository accountRepository;
    private TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public void transfer(Long fromAccountId,
                         Long toAccountId,
                         BigDecimal amount){

        Account fromAccount =
                accountRepository.findById(fromAccountId) //fetching account from which amount will be debited
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Account toAccount =
                accountRepository.findById(toAccountId)  // fetching the account in which amount will be credited
                .orElseThrow(() -> new RuntimeException("User Not Found"));

         fromAccount.debitAcoount(amount);  //some amount debited from a account's balance
         accountRepository.saveAndFlush(fromAccount);

         toAccount.creditAcoount(amount); //same amount credited to another account's balance
        accountRepository.saveAndFlush(toAccount);


        transferRepository.save(new TransferRecord( //transfered record hAs been saved
                fromAccountId,
                toAccountId,
                amount,
                LocalDateTime.now()
        ));
        transferRepository.flush();

      //  throw new RuntimeException("some error occured");

        //**important --->>>>  flush ka mtlb commit ni hota

        //agar save and flush har operation k baad krte hai lekin  exception bhi last me  throw krte hain to database me commit ni hoga. save and flush k baad bhi roll back ho jayega due to @transactional
        //agar @transactional ni hai to save and flush krne p data base me jyega.
        //hence save and flush != commit--->> commit only be done when all function runs without any exception at time of @transactional

       // @Transactional works on ACID principal
        //A --->Atomicity
        //C ---> consistency
        //I--> Isolation
        //D--->Durability

    }
    }

