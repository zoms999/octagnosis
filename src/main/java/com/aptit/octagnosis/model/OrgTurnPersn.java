package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrgTurnPersn {
    @Id
    private Long orgId;
    @Id
    private Long turnId;
    @Id
    private Long persnId;
    private String regDt;
    private String startDt;
    private String endDt;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
