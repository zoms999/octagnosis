package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.OrgParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrgMapper {

    int CretOrg(Org org);

    int EditOrg(Org org);
    
    long GetOrgId();
    Org GetOrgById(Long orgId);
    int GetOrgListTotCnt(OrgParm orgParm);
    List<Map<String, String>> GetOrgList(OrgParm orgParm);
    Org GetExistOrg(OrgParm orgParm);
}
