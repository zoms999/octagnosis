package com.aptit.octagnosis.model;

import lombok.Data;

@Data
public class AcuntParm {
    private String acuntId;
    private String userType;
    private long userId;
    private String expirDt;
    private String pw;

    private MngrLog mngrLog;

}
