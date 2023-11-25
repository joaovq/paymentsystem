package br.com.joaovq.paymentsystemservice.entity;

import java.math.BigDecimal;
import java.util.List;

public record TransactionReport(
        String nameStore,
        BigDecimal total,
        List<Transaction> transactions
) {
    public TransactionReport addTotal(BigDecimal value) {
        return new TransactionReport(
                nameStore,
                total.add(value),
                transactions
        );
    }

    public TransactionReport addTransaction(Transaction transaction) {
        transactions.add(transaction);
        return new TransactionReport(
                nameStore,
                total,
                transactions
        );
    }
}
