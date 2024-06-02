package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.modelParm.OrgParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrgMapper {

    int cretOrg(Org org);

    int editOrg(Org org);

    int editUrlCd(OrgParm orgParm);


    long getOrgId();

    Org getOrgById(Long orgId);

    int getOrgListTotCnt(OrgParm orgParm);

    List<Map<String, String>> getOrgList(OrgParm orgParm);

    Org getExistOrg(OrgParm orgParm);
}
