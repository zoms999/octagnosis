package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.OrgTurn;
import com.aptit.octagnosis.modelParm.OrgTurnParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrgTurnMapper {
    int cretOrgTurn(OrgTurn orgTurn);

    int editOrgTurnUse(OrgTurnParm orgTurn);

    OrgTurn getExistTurnConnCd(OrgTurnParm orgTurnParm);

    List<OrgTurn> getOrgTurnList(OrgTurnParm orgTurnParm);


    OrgTurn getOrgMaxTurn(OrgTurn orgTurn);

    int getOrgTurnPersnTotCnt(OrgTurnParm orgTurnParm);

    List<Map<String, String>> getOrgTurnPersnList(OrgTurnParm orgTurnParm);

}
