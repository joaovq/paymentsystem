package br.com.joaovq.paymentsystemservice.domain;

import java.math.BigDecimal;

public record TransactionCNAB(
        Integer type,
        String date,
        BigDecimal value,
        Long cpf,
        String card,
        String time,
        String ownerStore,
        String nameStore
) {

}
