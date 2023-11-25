package br.com.joaovq.paymentsystemservice.repository;


import br.com.joaovq.paymentsystemservice.entity.Transaction;
import br.com.joaovq.paymentsystemservice.entity.TransactionReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    //select * from transaction order by name_store asc and id desc
    List<Transaction> findAllByOrderByNameStoreAscIdDesc();
}
