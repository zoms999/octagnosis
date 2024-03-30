package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrgTurn {
    @Id
    private Long orgId;
    @Id
    private Long turnId;
    private String useYn;
    private Long turnNum;
    private Long turnReqCnt;
    private Long turnUseCnt;
    private String turnConnCd;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
