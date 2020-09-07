package com.toaa.walletcontrol.service;

import com.toaa.walletcontrol.database.PaymentRepository;
import com.toaa.walletcontrol.model.wallet.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        newPayment.setDate(payment.getDate());
        newPayment.setDetail(payment.getDetail());
        newPayment.setPrice(payment.getPrice());
        newPayment.setProduct(payment.getProduct());
        return paymentRepository.save(newPayment);
    }

}
