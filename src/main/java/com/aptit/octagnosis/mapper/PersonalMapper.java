package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.Personal;
import com.aptit.octagnosis.req.PersonalRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonalMapper {
    List<Personal> getPersonalList2(Map<String, Object> params);
    int getPersonalCount2(Map<String, Object> params);

    int getPersonalCount(PersonalRequest request);
    List<Org> getPersonalList(PersonalRequest request);
}