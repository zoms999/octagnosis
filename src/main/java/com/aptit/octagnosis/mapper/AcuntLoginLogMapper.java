package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.AcuntLoginLog;
import com.aptit.octagnosis.model.AcuntLoginLogParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AcuntLoginLogMapper {
    int CretAcuntLoginLog(AcuntLoginLog acuntLoginLog);

    int GetAcuntLoginLogListTotCnt(AcuntLoginLogParm acuntLoginLogParm);

    List<Map<String, String>> GetAcuntLoginLogList(AcuntLoginLogParm acuntLoginLogParm);


}
