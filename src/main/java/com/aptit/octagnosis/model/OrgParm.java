package com.aptit.octagnosis.model;

import lombok.Data;

@Data
public class OrgParm {
    private Long orgId;
    private String orgNm;
    private String srchStr;

    private Paging paging;
    private AcuntLog acuntLog;

    private String urlCd;
    private String urlCdNew;
    private String actionReasn;

    private long userId;
}
