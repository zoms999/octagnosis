package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.OrgTurn;
import com.aptit.octagnosis.model.OrgTurnParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrgTurnMapper {
    int CretOrgTurn(OrgTurn orgTurn);

    int EditOrgTurnUse(OrgTurnParm orgTurn);

    OrgTurn GetExistTurnConnCd(OrgTurnParm orgTurnParm);

    List<OrgTurn> GetOrgTurnList(OrgTurnParm orgTurnParm);


    OrgTurn GetOrgMaxTurn(OrgTurn orgTurn);

    int GetOrgTurnPersnTotCnt(OrgTurnParm orgTurnParm);

    List<Map<String, String>> GetOrgTurnPersnList(OrgTurnParm orgTurnParm);

}
