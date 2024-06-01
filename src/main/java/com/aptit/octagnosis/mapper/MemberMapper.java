package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Personal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Select("SELECT COUNT(*) = 0 FROM TB_Acunt WHERE acuntId = #{acuntId}")
    boolean isAcountIdDuplicate(String acuntId);
    void registerPersonal(Personal personal);

    //유효성 체크
    @Select("SELECT CompyNm FROM TB_Org WHERE UrlCd = #{urlCd}")
    String getCompyNmByUrlCd(String urlCd);

    @Select("SELECT * FROM TB_Acunt WHERE AcuntId = #{acuntId} AND Pw = #{pw}")
    Acunt findAcuntByIdAndPw(String acuntId, String pw);

}
