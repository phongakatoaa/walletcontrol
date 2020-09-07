package com.toaa.walletcontrol.controller;

import com.toaa.walletcontrol.model.wallet.Payment;
import com.toaa.walletcontrol.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Payment createPayment(Payment payment) {
        return paymentService.create(payment);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Payment getPayment(@PathVariable long id) {
        return paymentService.getById(id);
    }
}
