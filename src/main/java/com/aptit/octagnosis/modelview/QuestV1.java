package com.aptit.octagnosis.modelview;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class QuestV1 {
    @Id
    private Long testId;
    @Id
    private Long questPageId;
    @Id
    private Long questId;
    private Long itemId;
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
    private String questTypeNm;
    private String questAttrCd1Nm;
    private String questAttrCd2Nm;

    private int itemCnt;
    private int imgCnt;

    private String val1;
    private String val2;

    
}
