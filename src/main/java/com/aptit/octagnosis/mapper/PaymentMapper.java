package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.ProdtPay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void savePayment(ProdtPay prodtPay);
}
