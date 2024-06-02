package com.aptit.octagnosis.modelview;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class QuestPageV1{
    public Long testId;
    public Long questPageId;
    public String questPageNm;
    public int questPageSeq;
    public String questPageType;
    public String questPageTypeNm;
    
    public List<QuestV1> questList;
    
}




