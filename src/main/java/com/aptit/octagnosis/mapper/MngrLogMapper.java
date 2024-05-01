package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.AcuntLogParm;
import com.aptit.octagnosis.model.MngrLog;
import com.aptit.octagnosis.model.MngrLogParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MngrLogMapper {
    int cretMngrLog(MngrLog mngrLog);

    long getLogId();
    int getMngrLogListTotCnt(MngrLogParm acuntLogParm);
    
    List<Map<String, String>> getMngrLogList(MngrLogParm cuntLogParm);

}
