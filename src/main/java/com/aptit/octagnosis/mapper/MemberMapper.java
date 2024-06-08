package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.Personal;
import com.aptit.octagnosis.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("SELECT COUNT(*) = 0 FROM TB_Acunt WHERE acuntId = #{acuntId}")
    boolean isAcountIdDuplicate(String acuntId);
    void registerPersonal(Personal personal);

    //유효성 체크
    @Select("SELECT OrgId, CompyNm FROM TB_Org WHERE UrlCd = #{urlCd}")
    Org getCompyNmByUrlCd(String urlCd);
    
    
/*    @Select("SELECT * FROM TB_Acunt WHERE AcuntId = #{acuntId} AND Pw = #{pw}")
    Acunt findAcuntByIdAndPw(String acuntId, String pw);*/

    @Select("SELECT a.*, b.* FROM TB_Acunt a LEFT JOIN TB_Persn b ON a.userId = b.persnId WHERE a.AcuntId = #{acuntId} AND a.Pw = #{pw}")
    @Results({
            @Result(property = "acuntId", column = "AcuntId"),
            @Result(property = "userType", column = "UserType"),
            @Result(property = "userId", column = "UserId"),
            @Result(property = "pw", column = "Pw"),
            @Result(property = "useYn", column = "UseYn"),
            @Result(property = "regDt", column = "RegDt"),
            @Result(property = "leaveDt", column = "LeaveDt"),
            @Result(property = "expirDt", column = "ExpirDt"),
            @Result(property = "termsUse", column = "TermsUse"),
            @Result(property = "termsPersn", column = "TermsPersn"),
            @Result(property = "termsEvent", column = "TermsEvent"),
            @Result(property = "insId", column = "InsId"),
            @Result(property = "insDt", column = "InsDt"),
            @Result(property = "uptId", column = "UptId"),
            @Result(property = "uptDt", column = "UptDt"),
            @Result(property = "personal.persnId", column = "PersnId"),
            @Result(property = "personal.persnNm", column = "PersnNm"),
            @Result(property = "personal.birthDate", column = "BirthDate"),
            @Result(property = "personal.birthYear", column = "BirthYear"),
            @Result(property = "personal.birthMonth", column = "BirthMonth"),
            @Result(property = "personal.birthDay", column = "BirthDay"),
            @Result(property = "personal.sex", column = "Sex"),
            @Result(property = "personal.phone", column = "Phone"),
            @Result(property = "personal.tel", column = "Tel"),
            @Result(property = "personal.email", column = "Email"),
            @Result(property = "personal.zip", column = "Zip"),
            @Result(property = "personal.addrStret", column = "AddrStret"),
            @Result(property = "personal.addrLotNum", column = "AddrLotNum"),
            @Result(property = "personal.addr2", column = "Addr2"),
            @Result(property = "personal.addr3", column = "Addr3"),
            @Result(property = "personal.educt", column = "Educt"),
            @Result(property = "personal.eductStus", column = "EductStus"),
            @Result(property = "personal.scholNm", column = "ScholNm"),
            @Result(property = "personal.scholMajor", column = "ScholMajor"),
            @Result(property = "personal.scholGrade", column = "ScholGrade"),
            @Result(property = "personal.job", column = "Job"),
            @Result(property = "personal.jobNm", column = "JobNm"),
            @Result(property = "personal.jobDuty", column = "JobDuty"),
            @Result(property = "personal.insId", column = "InsId"),
            @Result(property = "personal.insDt", column = "InsDt"),
            @Result(property = "personal.uptId", column = "UptId"),
            @Result(property = "personal.uptDt", column = "UptDt")
    })
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

    @Select("SELECT ProdtId, Price, DcRate, ProdtNm, ProdtCate, UsePerid, UseYn, ProdtType, InsId, InsDt, UptId, UptDt FROM TB_Prodt")
    List<Product> getAllProducts();
}
