package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.modelParm.AcuntParm;
import com.aptit.octagnosis.modelParm.MainParm;

import com.aptit.octagnosis.modelParm.OrgParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AcuntMapper {
    int cretAcunt(Acunt acunt);

    int editAcunt(Acunt acunt);

    int updateAcunt(Acunt acunt);

    int editExpirDt(AcuntParm acuntParm);

    int editPw(AcuntParm acuntParm);

    Acunt getAcunt(String acuntId);

    Acunt getAcuntByUserId(AcuntParm acuntParm);

    int getAcuntListTotCnt(AcuntParm acuntParm);

    List<Acunt> getAcuntList(AcuntParm acuntParm);

    Acunt getExistAcunt(AcuntParm acuntParm);
    
    
}
