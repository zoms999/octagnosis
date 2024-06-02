package com.aptit.octagnosis.modelParm;

import lombok.Data;

@Data
public class AcuntLoginLogParm {
    private String srchStr;
    private String acuntId;

    private Paging paging;

}
