package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AnsPrgrs {
    @Id
    private Long AnsPrgrsId;
    private Long TestId;
    private Long QuestPageId;
    private String StartDt;
    private String EndDt;
    private String DoneYn;
    private String AcuntId;
    private Long ProdtId;
    private Long TurnId;
    private Long PayId;
    private Long InsId;
    private String InsDt;
    private Long UptId;
    private String UptDt;
}
