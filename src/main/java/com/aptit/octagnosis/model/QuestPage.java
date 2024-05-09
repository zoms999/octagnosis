package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class QuestPage {
    @Id
    private Long testId;
    private Long questPageId;
    private String questPageNm;
    private int questPageSeq;
    private String questPageType;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
