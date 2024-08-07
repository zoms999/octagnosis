package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.AcuntLoginLog;
import com.aptit.octagnosis.modelParm.AcuntLoginLogParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AcuntLoginLogMapper {
    int cretAcuntLoginLog(AcuntLoginLog acuntLoginLog);

    int getAcuntLoginLogListTotCnt(AcuntLoginLogParm acuntLoginLogParm);

    List<Map<String, String>> getAcuntLoginLogList(AcuntLoginLogParm acuntLoginLogParm);


}
