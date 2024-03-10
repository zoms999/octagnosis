package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.ManagerMapper;
import com.aptit.octagnosis.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping("/managers/{mngrId}")
    public ResponseEntity<Manager> updatePost(@PathVariable("mngrId") Long mngrId, @RequestBody Manager manager) {
        manager.setMngrId(mngrId); // Set the id in case it's not provided in the request body
        managerService.updateManager(manager);
        return ResponseEntity.ok(manager);
    }
}