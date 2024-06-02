package com.aptit.octagnosis.modelParm;

import lombok.Data;

@Data
public class AcuntLogParm {
    private String srchStr;
    private String actinType;
    private String acuntId;

    private Paging paging;

}
