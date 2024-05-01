package com.aptit.octagnosis.model;

import lombok.Data;

@Data
public class MngrLogParm {
    private String srchStr;
    private String actinType;
    private String mngrId;

    private Paging paging;

}
