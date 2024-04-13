package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AcuntLoginLog {
    @Id
    private Long logId;
    private String acuntId;
    private String LoginDt;
    private String loginEnv;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
    
}
