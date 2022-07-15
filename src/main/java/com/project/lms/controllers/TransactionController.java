package com.project.lms.controllers;

import com.project.lms.errors.TransactionException;
import com.project.lms.services.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/issue")
    public String issueTxn(@RequestParam int studentId, @RequestParam int bookId) throws TransactionException {
        return transactionService.issueTxn(studentId,bookId);
    }

    @PostMapping("/return")
    public String returnTxn(@RequestParam int studentId, @RequestParam int bookId) throws TransactionException {
        return transactionService.returnTxn(studentId,bookId);
    }

}
