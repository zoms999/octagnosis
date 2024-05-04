package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Compy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompyMapper {
    int cretCompy(Compy compy);

    int editCompy(Compy compy);
    
    Compy getCompy();
}
