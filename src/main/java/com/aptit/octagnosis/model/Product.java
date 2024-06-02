package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private long prodId;
    private long price;
    private int dcRate;
    private String prodNm;
    private String prodCate;
    private char useYn;
    private String prodType;
    private long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
