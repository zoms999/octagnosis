package com.aptit.octagnosis.req;

import com.aptit.octagnosis.model.Paging;
import lombok.Data;

@Data
public class PersonalRequest {
    private Long persnId;
    private String persnNm;
    private String srchStr;

    private Paging paging;

    private String urlCd;
    private String actionReasn;
}
