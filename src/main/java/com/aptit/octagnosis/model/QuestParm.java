package com.aptit.octagnosis.model;

import lombok.Data;

@Data
public class QuestParm {
    private String srchStr;
    
    private String procType;
    private long testId;
    private long questPageId;
    private long oldQuestPageId;
    private long questId;
    private long itemId;

}
