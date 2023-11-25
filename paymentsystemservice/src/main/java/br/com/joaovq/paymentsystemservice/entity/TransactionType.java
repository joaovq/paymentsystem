package br.com.joaovq.paymentsystemservice.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public enum TransactionType {
    DEBIT(1), TICKET(2), FINANCING(3), CREDIT(4),
    LOAN_RECEIPT(5), SALES(6), TED_RECEIPT(7), DOC_RECEIPT(8),
    RENT(9);
    private int type;

    TransactionType(int type) {
        this.type = type;
    }

    public BigDecimal getSignal() {
        return switch (type) {
            case 1, 4, 5, 6, 7, 8 -> new BigDecimal(1);
            case 2, 3, 9 -> new BigDecimal(-1);
            default -> new BigDecimal(0);
        };
    }

    public static TransactionType findByType(int type) {
        Optional<TransactionType> first = Arrays.stream(values()).filter(transactionType -> transactionType.type == type).findFirst();
        return first.orElseThrow(IllegalArgumentException::new);
    }
}
