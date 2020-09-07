package com.toaa.walletcontrol.service;

import com.toaa.walletcontrol.database.PaymentRepository;
import com.toaa.walletcontrol.model.wallet.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private SecureUserService secureUserService;

    public Payment getById(long id){
        return paymentRepository.findById(id);
    }

    public Payment create(Payment payment) {
        Payment newPayment = new Payment();
        newPayment.setUser(secureUserService.getCurrentUser());
        newPayment.setDate(LocalDate.now());
        newPayment.setDetail(payment.getDetail());
        newPayment.setCost(payment.getCost());
        newPayment.setProduct(payment.getProduct());
        return paymentRepository.save(newPayment);
    }

    public List<Payment> getByTimeRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate startDateTime = LocalDate.parse(startDate, formatter);
        LocalDate endDateTime = LocalDate.parse(endDate, formatter);
        return paymentRepository.findAllByDateBetween(startDateTime, endDateTime);
    }

    public long getByYear(int year) {

        return paymentRepository.getSum();
    }
}
