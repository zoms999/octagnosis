package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.PaymentMapper;
import com.aptit.octagnosis.model.ProdtPay;
import com.aptit.octagnosis.model.ProdtPayDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentMapper paymentMapper;

    @PostMapping("/payment/save")
    public String savePayment(@RequestBody ProdtPay prodtPay) {
        paymentMapper.savePayment(prodtPay);
        return "Payment information saved successfully.";
    }

    @GetMapping("/payment/all")
    public ResponseEntity<List<ProdtPayDetail>> getAllPayments() {
        List<ProdtPayDetail> payments = paymentMapper.findAllPayments();
        return ResponseEntity.ok(payments);
    }
}
