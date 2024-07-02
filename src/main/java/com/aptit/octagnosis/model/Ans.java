package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Ans {
    @Id
    private Long ansPrgrsId;
    private Long questId;
    private String val1;
    private String val2;
    private BigDecimal weigt;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
