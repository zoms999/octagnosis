package com.aptit.octagnosis.req;

import com.aptit.octagnosis.model.Paging;
import lombok.Data;

@Data
public class ManagerRequest {
    private Long mngrId;
    private String mngrNm;
    private String srchStr;

    private Paging paging;

    private String urlCd;
    private String actionReasn;
}
