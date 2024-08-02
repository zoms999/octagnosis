package com.aptit.octagnosis.modelParm;

import com.aptit.octagnosis.modelview.QuestV1;
import lombok.Data;

import java.util.List;

@Data
public class TestParm {
    private String srchStr;
    
    private String procType;
    private long testId;
    private long questPageId;
    private long oldQuestPageId;
    private long questId;
    private long itemId;
    private long prodtId;
    private long turnId;
    private long payId;


    private String acuntId;
    private long orgId;
    private long persnId;
    private String turnConnCd;
    private long ansPrgrsId;

    private List<QuestV1> questList;

    private long insId;
    private long uptId;
    
    private Paging paging;
}
