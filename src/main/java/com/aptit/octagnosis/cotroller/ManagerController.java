package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.ManagerMapper;
import com.aptit.octagnosis.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/managers/check-duplicate-email")
    public Map<String, Boolean> checkDuplicateEmail(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        boolean exists = managerService.checkDuplicateEmail(email) > 0;
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    @PatchMapping("/managers/chg/{mngrId}")
    public ResponseEntity<String> updatePassword(@PathVariable("mngrId") Long mngrId, @RequestBody Map<String, String> requestBody) {
        String currentPassword = requestBody.get("currentPassword");
        String newPassword = requestBody.get("newPassword");

        Manager manager = managerService.getManagerById(mngrId);

        if (manager != null && manager.getPw().equals(currentPassword)) {
            managerService.updatePassword(mngrId, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Current password is incorrect");
        }
    }

    //권한수정
    @PatchMapping("/managers/auth/{mngrId}")
    public ResponseEntity<String> updateManagerAuthorization(@PathVariable("mngrId") Long mngrId, @RequestBody Map<String, Integer> authUpdates) {
        Manager manager = managerService.getManagerById(mngrId);

        if (manager != null) {
            // Update only the provided authorization fields
            if (authUpdates.containsKey("authAdmin")) {
                manager.setAuthAdmin(authUpdates.get("authAdmin"));
            }
            else if (authUpdates.containsKey("authOrg")) {
                manager.setAuthOrg(authUpdates.get("authOrg"));
            }
            else if (authUpdates.containsKey("authPersn")) {
                manager.setAuthPersn(authUpdates.get("authPersn"));
            }
            else if (authUpdates.containsKey("authBbs")) {
                manager.setAuthBbs(authUpdates.get("authBbs"));
            }
            else if (authUpdates.containsKey("authRsltView")) {
                manager.setAuthRsltView(authUpdates.get("authRsltView"));
            }
            else if (authUpdates.containsKey("authLogView")) {
                manager.setAuthLogView(authUpdates.get("authLogView"));
            }
            else if (authUpdates.containsKey("authStati")) {
                manager.setAuthStati(authUpdates.get("authStati"));
            }
            // Add other authorization fields similarly

            managerService.updateManagerAuthorization(manager);
            return ResponseEntity.ok("Authorization updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}