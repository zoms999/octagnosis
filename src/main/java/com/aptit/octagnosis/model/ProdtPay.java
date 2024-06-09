package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProdtPay {
    @Id
    private Long payId;
    private String dutyYn;
    private String studyYn;
    private String subjYn;
    private String imgYn;
    private String acuntId;
    private Long prodId;
    private String prodType;
    private Long price;
    private String payDt;
    private Long orgId;
    private Long turnId;
    private String payYn;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;

}
