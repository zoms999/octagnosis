package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Prodt;
import com.aptit.octagnosis.modelParm.ProdtParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProdtMapper {
    int cretProdt(Prodt prodt);
    
    int editProdt(Prodt prodt);

    int delProdt(long prodtId);
    
    Prodt getProdt(long prodtId);
    
    List<Prodt> getProdtList(ProdtParm prodtParm);
}
