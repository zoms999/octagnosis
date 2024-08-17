package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.PaymentMapper;
import com.aptit.octagnosis.model.ProdtPay;
import com.aptit.octagnosis.model.ProdtPayDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentMapper paymentMapper;

    @PostMapping("/payment/save")
    public ResponseEntity<ProdtPay> savePayment(@RequestBody ProdtPay prodtPay) {
        Integer turnId = paymentMapper.findNextTurnId(prodtPay.getAcuntId());
        prodtPay.setTurnId(Long.valueOf(turnId));
        paymentMapper.savePayment(prodtPay);
        return ResponseEntity.ok(prodtPay); // Return the saved ProdtPay object including payId
    }



    @DeleteMapping("/payment/delete/{payId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long payId) {
        paymentMapper.deletePayment(payId);
        return ResponseEntity.ok("Payment deleted successfully.");
    }

    @GetMapping("/payment/all")
    public ResponseEntity<List<ProdtPayDetail>> getAllPayments() {
        List<ProdtPayDetail> payments = paymentMapper.findAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/payment/updateStatus/{payId}")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long payId, @RequestBody Map<String, String> status) {
        String newStatus = status.get("status");
        String statusValue;

        // SUCCESS일 경우 'Y', FAIL일 경우 'N' 할당
        if (newStatus.equals("SUCCESS")) {
            statusValue = "Y";
        } else if (newStatus.equals("FAIL")) {
            statusValue = "N";
        } else {
            return ResponseEntity.badRequest().body("Invalid status");
        }

        // Status 값을 DB에 업데이트
        paymentMapper.updatePaymentStatus(payId, statusValue);
        return ResponseEntity.ok("Payment status updated successfully.");
    }
}
