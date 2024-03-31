package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.Personal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonalMapper {
    List<Personal> getPersonalList(Map<String, Object> params);

    int getPersonalCount(Map<String, Object> params);
}