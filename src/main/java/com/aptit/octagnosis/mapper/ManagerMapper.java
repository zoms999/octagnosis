package com.aptit.octagnosis.mapper;

import com.aptit.octagnosis.model.Manager;
import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.req.ManagerRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    Manager getManagerById(Long mngrId);
    List<Manager> getAllManagers();
    int getTotalManagerCount();

    int getManagerCount(ManagerRequest request);
    List<Manager> getManagerList(ManagerRequest request);

    void saveManager(Manager manager);
    void updateManager(Manager manager);

    Manager findByEmailAndPassword(String email, String password);

    int checkDuplicateEmail(String email);

    void updatePassword(Long mngrId, String newPassword);

    void updateManagerAuthorization(Manager manager);
}
