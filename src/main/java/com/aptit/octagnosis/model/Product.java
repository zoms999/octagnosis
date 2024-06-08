package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private long prodtId;
    private long price;
    private int dcRate;
    private String prodtNm;
    private String prodtCate;
    private Integer usePerid;
    private String useYn;
    private String prodtType;
    private long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
