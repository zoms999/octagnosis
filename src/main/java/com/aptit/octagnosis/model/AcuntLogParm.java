package com.aptit.octagnosis.model;

import lombok.Data;

@Data
public class AcuntLogParm {
    private String srchStr;
    private String actinType;
    private String acuntId;

    private Paging paging;

}
