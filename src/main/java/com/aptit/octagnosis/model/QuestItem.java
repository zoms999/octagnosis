package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class QuestItem {
    @Id
    private Long questId;
    @Id
    private Long itemId;
    private String itemType;
    private String conts;
    private String imgNm;
    private int seq;
    private BigDecimal weigt;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;

}
