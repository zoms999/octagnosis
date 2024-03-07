package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.ManagerMapper;
import com.aptit.octagnosis.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManagerController {

    @Autowired
    private ManagerMapper managerService;

    @GetMapping("/managers/{mngrId}")
    public Manager getManagerById(@PathVariable("mngrId") Long mngrId) {
        return managerService.getManagerById(mngrId);
    }

    @GetMapping("/managers")
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();
    }
}