package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.AcuntParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AcuntMapper {
    int CretAcunt(Acunt acunt);
    int EditAcunt(Acunt acunt);

    int EditExpirDt(AcuntParm acuntParm);
    int EditPw(AcuntParm acuntParm);

    Acunt GetAcunt(String acuntId);
    Acunt GetAcuntByUserId(AcuntParm acuntParm);
    int GetAcuntListTotCnt(AcuntParm acuntParm);
    List<Acunt> GetAcuntList(AcuntParm acuntParm);
    
    Acunt GetExistAcunt(AcuntParm acuntParm);
}
