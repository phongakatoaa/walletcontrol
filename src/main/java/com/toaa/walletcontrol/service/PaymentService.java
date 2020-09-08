package com.toaa.walletcontrol.service;

import com.toaa.walletcontrol.database.CategoryRepository;
import com.toaa.walletcontrol.database.PaymentRepository;
import com.toaa.walletcontrol.database.UserRepository;
import com.toaa.walletcontrol.model.dto.DTOPayment;
import com.toaa.walletcontrol.model.login.User;
import com.toaa.walletcontrol.model.wallet.Category;
import com.toaa.walletcontrol.model.wallet.Payment;
import org.slf4j.Logger;
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
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Logger logger;

    public Payment getById(long id) {
        return paymentRepository.findById(id);
    }

    public Payment create(DTOPayment payment) {
        Category category = categoryRepository.findById(payment.getCategoryId());
        logger.info("Category: " + category);
        User user = userRepository.findById(payment.getUserId());
        logger.info("User: " + user);
        Payment newPayment = new Payment();
        LocalDate date = LocalDate.parse(payment.getDate());
        logger.info("Date: " + date);
        newPayment.setUser(user);
        newPayment.setDate(date);
        newPayment.setDetail(payment.getDetail());
        newPayment.setCost(payment.getCost());
        newPayment.setProduct(payment.getProduct());
        newPayment.setCategory(category);
        return paymentRepository.save(newPayment);
    }

    public List<Payment> getByTimeRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate startDateTime = LocalDate.parse(startDate, formatter);
        LocalDate endDateTime = LocalDate.parse(endDate, formatter);
        return paymentRepository.findAllByDateBetween(startDateTime, endDateTime);
    }

    public long getByYear(int year) {
        return paymentRepository.getSum("", "");
    }
}
