package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.modelParm.MainParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MainMapper {
    List<Map<String, String>> getDashboard1(MainParm mainParm);
    List<Map<String, String>> getDashboard2(MainParm mainParm);
    List<Map<String, String>> getDashboard3(MainParm mainParm);
    List<Map<String, String>> getDashboard4(MainParm mainParm);
    
    
}
