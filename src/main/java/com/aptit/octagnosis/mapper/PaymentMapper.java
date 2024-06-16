package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.ProdtPay;
import com.aptit.octagnosis.model.ProdtPayDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    void savePayment(ProdtPay prodtPay);

    List<ProdtPayDetail> findAllPayments();
}
