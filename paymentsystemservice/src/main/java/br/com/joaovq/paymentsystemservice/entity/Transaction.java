package br.com.joaovq.paymentsystemservice.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Table(name = "transaction_tb")
public record Transaction(
        @Id Long id,
        Integer type,
        @Column(value = "transaction_date")
        Date date,
        @Column(value = "transaction_value")
        BigDecimal value,
        Long cpf,
        String card,
        @Column(value = "transaction_time")
        Time time,
        @Column("owner_store")
        String ownerStore,
        @Column("name_store")
        String nameStore
) {
    public Transaction withValue(BigDecimal value) {
        return new Transaction(
                id, type, date,
                value, cpf, card,
                time, ownerStore.trim(),
                nameStore);
    }

    public Transaction withDate(String date) throws ParseException {
        var dateFormat = new SimpleDateFormat("yyyyMMdd");
        var newDate = dateFormat.parse(date);

        return new Transaction(
                id, type, new Date(newDate.getTime()),
                value, cpf, card,
                time, ownerStore.trim(),
                nameStore);
    }

    public Transaction withTime(String time) throws ParseException {
        var dateFormat = new SimpleDateFormat("HHmmss");
        var newDate = dateFormat.parse(time);

        return new Transaction(
                id, type, date,
                value, cpf, card,
                new Time(newDate.getTime()), ownerStore.trim(),
                nameStore);
    }
}
