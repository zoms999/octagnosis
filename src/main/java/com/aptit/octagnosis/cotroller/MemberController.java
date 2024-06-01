package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.MemberMapper;
import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Personal;
import com.aptit.octagnosis.req.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberMapper memberService;

    @Autowired
    private AcuntMapper AcuntService;

    @PostMapping("/member/check-id")
    public Map<String, Boolean> checkIdDuplicate(@RequestBody Map<String, String> request) {
        String acuntId = request.get("acuntId");
        boolean isDuplicate = memberService.isAcountIdDuplicate(acuntId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return response;
    }

    @PostMapping("/member/validate-code")
    public ResponseEntity<Map<String, Object>> validateOrgCode(@RequestBody Map<String, String> request) {
        String urlCd = request.get("urlCd");
        String compyNm = memberService.getCompyNmByUrlCd(urlCd);

        Map<String, Object> response = new HashMap<>();
        if (compyNm != null) {
            response.put("exists", true);
            response.put("compyNm", compyNm);
        } else {
            response.put("exists", false);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/member/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        Acunt acunt = request.getAcunt();
        Personal personal = request.getPersonal();
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = sdf.format(currentDate);
        try {
            memberService.registerPersonal(personal);
            acunt.setRegDt(formattedDate.toString());
            acunt.setUserId(personal.getPersnId());
            acunt.setInsId(personal.getPersnId());
            int result = AcuntService.cretAcunt(acunt);

            if (result > 0) {
                return ResponseEntity.ok("Registration successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
        }
    }

  /*  @PostMapping("/api/member")
    public void registerPersonal(@RequestBody Personal personal) {
        memberService.registerPersonal(personal);
    }*/

    @PostMapping("/member/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String acuntId = request.get("acuntId");
        String pw = request.get("pw");

        Map<String, Object> response = new HashMap<>();
        try {
            Acunt acunt = memberService.findAcuntByIdAndPw(acuntId, pw);
            if (acunt != null) {
                response.put("success", true);
                response.put("acunt", acunt);
            } else {
                response.put("success", false);
                response.put("message", "Invalid credentials");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred during login");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }
}
