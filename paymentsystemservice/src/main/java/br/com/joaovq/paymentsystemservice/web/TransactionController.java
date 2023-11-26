package br.com.joaovq.paymentsystemservice.web;


import br.com.joaovq.paymentsystemservice.entity.Transaction;
import br.com.joaovq.paymentsystemservice.entity.TransactionReport;
import br.com.joaovq.paymentsystemservice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    ResponseEntity<List<TransactionReport>> listAll() {
        return ResponseEntity.ok().body(transactionService.getAllTransactionForNameStore());
    }
}
