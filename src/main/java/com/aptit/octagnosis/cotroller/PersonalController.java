package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.PersonalMapper;
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
}
