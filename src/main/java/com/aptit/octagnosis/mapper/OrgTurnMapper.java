package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.OrgTurn;
import com.aptit.octagnosis.model.OrgTurnParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrgTurnMapper {
    int CretOrgTurn(OrgTurn orgTurn);
    int EditOrgTurn(OrgTurn orgTurn);
    OrgTurn GetExistTurnConnCd(OrgTurnParm orgTurn);
    OrgTurn GetOrgMaxTurn(OrgTurnParm orgTurn);
    
}
