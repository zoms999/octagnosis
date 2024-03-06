package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    Manager getManagerById(Long mngrId);
    List<Manager> getAllManagers();
}
