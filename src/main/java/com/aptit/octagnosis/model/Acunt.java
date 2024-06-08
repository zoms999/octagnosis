package com.aptit.octagnosis.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Acunt {
    @Id
    private String acuntId;
    private String userType;
    private Long userId;
    private String pw;
    private String useYn;
    private String regDt;
    private String leaveDt;
    private String expirDt;
    private String termsUse;
    private String termsPersn;
    private String termsEvent;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "persnId", insertable = false, updatable = false)
    private Personal personal;
}
