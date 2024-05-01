package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MngrLog {
    @Id
    private Long logId;
    private Long mngrId;
    private String actinDt;
    private String actinType;
    private String actinReasn;
    private String actinRslt;
    private String actinFunc;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
    
}
