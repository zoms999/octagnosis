package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Prodt {
    @Id
    private Long    prodtId;
    private String  price;
    private int     dcRate;
    private String  prodtNm;
    private String  prodtCate;
    private int     usePerid;
    private String  useYn;
    private String  prodtType;
    private Long    insId;
    private String  insDt;
    private Long    uptId;
    private String  uptDt;

}
