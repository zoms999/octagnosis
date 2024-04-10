package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.Personal;
import com.aptit.octagnosis.modelview.PersonalAcuntView;
import com.aptit.octagnosis.req.PersonalRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonalMapper {
    List<Personal> getPersonalList2(Map<String, Object> params);
    int getPersonalCount2(Map<String, Object> params);

    int getPersonalCount(PersonalRequest request);
    List<Org> getPersonalList(PersonalRequest request);

    Personal getPersonalById(Long persnId);

    Acunt getAccountlById(Long persnId);
    void accountUpdatePassword(Long persnId, String newPassword);

    Map<String, Object> selectPersnByUserIdAndType(String persnId);

    void updatePersonalData(PersonalAcuntView personal);

    void updateAccountExpirDt(Long persnId, String expirDt);

}