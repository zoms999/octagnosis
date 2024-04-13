package com.aptit.octagnosis.model;

import lombok.Data;

@Data
public class OrgTurnParm {
    private Long orgId;
    private Long turnId;
    private String turnConnCd;
    private String useYn;
    private long userId;

    private Paging paging;

}
