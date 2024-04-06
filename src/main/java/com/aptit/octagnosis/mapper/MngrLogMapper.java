package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.MngrLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MngrLogMapper {
    int CretMngrLog(MngrLog mngrLog);
    
    long GetLogId();
    
}
