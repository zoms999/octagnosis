package com.aptit.octagnosis.model;

import lombok.Data;

@Data
public class AcuntLoginLogParm {
    private String srchStr;
    private String acuntId;

    private Paging paging;

}
