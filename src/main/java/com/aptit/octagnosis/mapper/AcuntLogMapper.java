package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.AcuntLog;
import com.aptit.octagnosis.modelParm.AcuntLogParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AcuntLogMapper {
    int cretAcuntLog(AcuntLog acuntLog);

    long getLogId();

    int getAcuntLogListTotCnt(AcuntLogParm acuntLogParm);

    List<Map<String, String>> getAcuntLogList(AcuntLogParm cuntLogParm);


}
