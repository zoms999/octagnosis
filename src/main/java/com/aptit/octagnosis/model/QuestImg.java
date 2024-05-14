package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class QuestImg {
    @Id
    private Long questId;
    @Id
    private Long imgId;
    private String imgType;
    private String conts;
    private String imgNm;
    private int seq;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;

}
