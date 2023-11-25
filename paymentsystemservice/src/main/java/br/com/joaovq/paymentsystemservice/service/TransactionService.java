package br.com.joaovq.paymentsystemservice.service;

import br.com.joaovq.paymentsystemservice.entity.Transaction;
import br.com.joaovq.paymentsystemservice.entity.TransactionReport;
import br.com.joaovq.paymentsystemservice.entity.TransactionType;
import br.com.joaovq.paymentsystemservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionReport> getAllTransactionForNameStore() {
        var transactions = transactionRepository.findAllByOrderByNameStoreAscIdDesc();
        var reportMap = new LinkedHashMap<String, TransactionReport>();
        transactions.forEach(transaction -> {
            String nameStore = transaction.nameStore();
            var transactionType = TransactionType.findByType(transaction.type());
            BigDecimal value = transaction.value().multiply(transactionType.getSignal());
            reportMap.compute(
                    nameStore, (key, existingReport) -> {
                        var report = (existingReport != null) ? existingReport : new TransactionReport(key,
                                BigDecimal.ZERO, new ArrayList<>());
                        transaction.withValue(value);
                        return report.addTotal(value).addTransaction(transaction);
                    }
            );
        });
        return new ArrayList<>(reportMap.values());
    }
}
