package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.OrgParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrgMapper {
    int CretOrg(Org org);
    int EditOrg(Org org);
    Org GetOrgById(Long orgId);
    int GetOrgListTotCnt(OrgParm orgParm);
    List<Org> GetOrgList(OrgParm orgParm);

}
