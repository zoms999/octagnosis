package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
