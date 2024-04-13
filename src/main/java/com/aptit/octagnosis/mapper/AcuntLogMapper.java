package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.AcuntLog;
import com.aptit.octagnosis.model.AcuntLogParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AcuntLogMapper {
    int CretAcuntLog(AcuntLog acuntLog);

    long GetLogId();

    int GetAcuntLogListTotCnt(AcuntLogParm acuntLogParm);

    List<Map<String, String>> GetAcuntLogList(AcuntLogParm cuntLogParm);


}
