package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Personal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("SELECT a.AcuntId " +
            "FROM TB_Acunt a " +
            "JOIN TB_Persn p ON a.UserId = p.PersnId " +
            "WHERE p.PersnNm = #{name} AND p.Email = #{email}")
    String findAcuntIdByNameAndEmail(@Param("name") String name, @Param("email") String email);

    @Select("SELECT a.AcuntId " +
            "FROM TB_Acunt a " +
            "JOIN TB_Persn p ON a.UserId = p.PersnId " +
            "WHERE a.AcuntId = #{userId} AND p.Email = #{email}")
    String findPasswordByEmailAndUserId(@Param("userId") String userId, @Param("email") String email);
}
