package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.PersonalMapper;
import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Personal;
import com.aptit.octagnosis.req.PersonalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonalController {

    @Autowired
    private PersonalMapper personalMapper;

/*    @PostMapping("/Personal/PersonalList")
    public List<Personal> getPersonalList(@RequestBody PersonalRequest request) {
        return personalMapper.getPersonalList(request);
    }*/


    @PostMapping("/personal/personalList")
    public Map<String, Object> getPersonalList(@RequestBody PersonalRequest request) {

        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("PersonalTotCnt", personalMapper.getPersonalCount(request));
        Rtn.put("PersonalList" , personalMapper.getPersonalList(request));
        return Rtn;
    }

    @PostMapping("/personal/personalList2")
    public ResponseEntity<?> getPersonalList2(@RequestBody Map<String, Object> params) {
        try {
            int start = (int) params.getOrDefault("start", 0);
            int limit = (int) params.getOrDefault("limit", 10); // 기본값은 10으로 설정하거나 필요에 따라 조절합니다.

            params.put("start", start);
            params.put("limit", limit);

            List<Personal> personalList = personalMapper.getPersonalList2(params);
            int totalCount = personalMapper.getPersonalCount2(params);
            Map<String, Object> response = new HashMap<>();
            response.put("PersonalList", personalList);
            response.put("PersonalTotCnt", totalCount); // 변수명 수정: "PersonaTotCnt" → "PersonalTotCnt"
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving personal list");
        }
    }

    //Edit
    @GetMapping("/personal/getUser/{persnId}")
    public Personal getManagerById(@PathVariable("persnId") Long persnId) {
        return personalMapper.getPersonalById(persnId);
    }

    @PostMapping("/personal/getPersnByPersnIdAndType")
    public Map<String, Object> getPersnByPersnIdAndType(@RequestBody Map<String, String> requestData) {
        String persnId = requestData.get("persnId");
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("Personal", personalMapper.selectPersnByUserIdAndType(persnId));
        return Rtn;
    }


    @PatchMapping("/personal/chgpw/{persnId}")
    public ResponseEntity<String> updatePassword(@PathVariable("persnId") Long persnId, @RequestBody Map<String, String> requestBody) {
        try {
            String currentPassword = requestBody.get("currentPassword");
            String newPassword = requestBody.get("newPassword");

            Acunt acunt = personalMapper.getAccountlById(persnId);

            if (acunt != null && acunt.getPw().equals(currentPassword)) {
                personalMapper.accountUpdatePassword(persnId, newPassword);
                return ResponseEntity.ok("Password updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Current password is incorrect");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
        }
    }

    @PatchMapping("/personal/chgpww/{persnId}")
    public ResponseEntity<String> updatePassword1(@PathVariable("persnId") Long persnId, @RequestBody Map<String, String> requestBody) {
        try {
            String currentPassword = requestBody.get("currentPassword");
            String newPassword = requestBody.get("newPassword");

            Acunt acunt = personalMapper.getAccountlById(persnId);

            if (acunt != null && acunt.getPw().equals(currentPassword)) {
                personalMapper.accountUpdatePassword(persnId, newPassword);
                return ResponseEntity.ok("Password updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Current password is incorrect");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
        }
    }


    @PostMapping("/personal/edit/{persnId}")
    public ResponseEntity<String> updatePersonalData(@PathVariable("persnId") Long persnId, @RequestBody Personal personal) {
        try {
            personalMapper.updatePersonalData(personal);
            return ResponseEntity.ok("Personal data updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
        }
    }
}
