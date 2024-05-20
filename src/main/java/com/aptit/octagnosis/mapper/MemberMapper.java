package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Personal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Select("SELECT COUNT(*) = 0 FROM TB_Acunt WHERE acuntId = #{acuntId}")
    boolean isAcountIdDuplicate(String acuntId);
    void registerPersonal(Personal personal);
}
