package br.com.joaovq.paymentsystemservice.service;

import br.com.joaovq.paymentsystemservice.entity.Transaction;
import br.com.joaovq.paymentsystemservice.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void shouldListAllTransactionForNameStore() {
        //given
        final String storeA = "Store A", storeB = "Store B";
        Random random = new Random();
        var transaction =
                new Transaction(null, 1, new Date(System.currentTimeMillis()), BigDecimal.valueOf(3000), random.nextLong(), "1234-5435-5466-6545", new Time(System.currentTimeMillis()), "Owner Store A", storeA);
        var transaction2 =
                new Transaction(null, 2, new Date(System.currentTimeMillis()), BigDecimal.valueOf(6000), random.nextLong(), "1234-5435-5466-6545", new Time(System.currentTimeMillis()), "Owner Store B", storeB);
        var mockTransactions = List.of(transaction, transaction2);
        when(transactionRepository.findAllByOrderByNameStoreAscIdDesc()).thenReturn(mockTransactions);
        //when
        var reports = transactionService.getAllTransactionForNameStore();
        //then
        Assertions.assertEquals(2, reports.size());

        reports.forEach(report -> {
            if (report.nameStore().equals(storeA)) {
                Assertions.assertEquals(1, report.transactions().size());
                Assertions.assertEquals(BigDecimal.valueOf(3000), report.total());
                Assertions.assertTrue(report.transactions().contains(transaction));
            }else if (report.nameStore().equals(storeB)) {
                Assertions.assertEquals(1, report.transactions().size());
                Assertions.assertEquals(BigDecimal.valueOf(-6000), report.total());
                Assertions.assertTrue(report.transactions().contains(transaction2));
            }
        });
    }
}
