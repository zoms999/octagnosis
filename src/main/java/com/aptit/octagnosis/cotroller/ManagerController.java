package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.ManagerMapper;
import com.aptit.octagnosis.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PatchMapping("/managers/{mngrId}")
    public Long  updatePost(@PathVariable("mngrId") Long mngrId, @RequestBody Manager manager) {
        manager.setMngrId(mngrId); // Set the id in case it's not provided in the request body
        managerService.updateManager(manager);
        return mngrId;
    }

    @PostMapping("/managers")
    public Long saveManager(@RequestBody Manager manager) {
        managerService.saveManager(manager);
        return manager.getMngrId();
    }

    @PostMapping("/login")
    public Manager login(@RequestBody Map<String, String> params) {
        Manager manager =  managerService.findByEmailAndPassword(params.get("email"), params.get("password"));
        if(manager != null) {
            return manager;
        } else {
            return null; // 로그인 실패 시 처리
        }
    }

    @PostMapping("/check-duplicate-email")
    public Map<String, Boolean> checkDuplicateEmail(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        boolean exists = managerService.checkDuplicateEmail(email) > 0;
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

}