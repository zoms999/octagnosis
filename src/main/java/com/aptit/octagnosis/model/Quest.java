package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Quest {
    @Id
    private Long testId;
    @Id
    private Long questPageId;
    @Id
    private Long questId;
    private int questNo;
    private String questCont1;
    private String questCont2;
    private String questCont3;
    private String questType;
    private String questAttrCd1;
    private String questAttrCd2;
    private int waitSec;
    private int itemColCnt;
    private int imgColCnt;
    private String useYn;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;

}
