package com.toaa.walletcontrol.database;

import com.toaa.walletcontrol.model.wallet.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);

    List<Payment> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query(value = "select sum(cost) from Payment where date between ?1 and ?2")
    long getSum(String startDate, String endDate);
}
