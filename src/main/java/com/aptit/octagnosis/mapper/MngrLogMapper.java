package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.MngrLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MngrLogMapper {
    int cretMngrLog(MngrLog mngrLog);

    long getLogId();

}
