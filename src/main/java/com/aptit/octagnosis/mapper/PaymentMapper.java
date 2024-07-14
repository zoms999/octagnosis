package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.ProdtPay;
import com.aptit.octagnosis.model.ProdtPayDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {

    void savePayment(ProdtPay prodtPay);

    void deletePayment(@Param("payId") Long payId);

    List<ProdtPayDetail> findAllPayments();

    void updatePaymentStatus(@Param("payId") Long payId, @Param("status") String status);
}
