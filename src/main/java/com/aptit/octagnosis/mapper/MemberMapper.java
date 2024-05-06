package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Personal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void registerPersonal(Personal personal);
}
