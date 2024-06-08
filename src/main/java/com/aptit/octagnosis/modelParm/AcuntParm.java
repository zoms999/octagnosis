package com.aptit.octagnosis.modelParm;

import com.aptit.octagnosis.model.AcuntLog;
import lombok.Data;

@Data
public class AcuntParm {
    private String acuntId;
    private String userType;
    private long userId;
    private String expirDt;
    private String pw;

    private AcuntLog acuntLog;

}
