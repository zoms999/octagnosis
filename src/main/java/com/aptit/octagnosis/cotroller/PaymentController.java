package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.PaymentMapper;
import com.aptit.octagnosis.model.ProdtPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentMapper paymentService;

    @PostMapping("/payment/save")
    public String savePayment(@RequestBody ProdtPay prodtPay) {
        paymentService.savePayment(prodtPay);
        return "Payment information saved successfully.";
    }
}
