package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.EmailSender;
import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.MemberMapper;
import com.aptit.octagnosis.mapper.PersonalMapper;
import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.Personal;
import com.aptit.octagnosis.req.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private PersonalMapper PersonalService;

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
        Org org = memberService.getCompyNmByUrlCd(urlCd);

        Map<String, Object> response = new HashMap<>();
        if (org  != null) {
            response.put("exists", true);
            response.put("orgId", org.getOrgId());
            response.put("compyNm", org.getCompyNm());
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
        String orgId = request.get("orgId");

        Map<String, Object> response = new HashMap<>();
        try {
            Acunt acunt = memberService.findAcuntByIdAndPw(acuntId, pw);
            if (acunt != null) {
                Personal persn = PersonalService.getPersonalById(acunt.getUserId());
                
                String PersnOrgId = String.valueOf(persn.getOrgId());
                
                if (!PersnOrgId.equals("0") && orgId.equals("0")) {
                    response.put("success", false);
                    response.put("errCode", "100");
                }  else {
                    if (orgId.equals("0")) {         // 기관 사용자
                        response.put("success", true);
                        response.put("acunt", acunt);
                        response.put("persn", persn);
                    } else {
                        if (PersnOrgId.equals(orgId)){
                            response.put("success", true);
                            response.put("acunt", acunt);
                            response.put("persn", persn);
                        } else {
                            response.put("success", false);
                            response.put("errCode", "200");
                        }
                    }
                }
            } else {
                response.put("success", false);
                response.put("errCode", "300");
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred during login");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }


    @PostMapping("/member/find-id")
    public ResponseEntity<Map<String, Object>> findId(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");

        Map<String, Object> response = new HashMap<>();
        try {
            String acuntId = memberService.findAcuntIdByNameAndEmail(name, email);
            if (acuntId != null) {
                response.put("success", true);
                response.put("acuntId", acuntId);
            } else {
                response.put("success", false);
                response.put("message", "일치하는 아이디가 없습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "아이디 찾기 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/member/find-password")
    public ResponseEntity<Map<String, Object>> findPassword(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String email = request.get("email");

        // 여기에 비밀번호 찾기 로직을 구현합니다.
        // userId와 email을 사용하여 데이터베이스에서 비밀번호를 찾고, 이메일로 임시 비밀번호를 전송합니다.
        String password = memberService.findPasswordByEmailAndUserId(userId, email);

        Map<String, Object> response = new HashMap<>();
        if (password != null) {
            // 비밀번호를 찾았을 경우 처리
            // 이메일로 임시 비밀번호 전송 등의 로직을 추가하세요.
            response.put("success", true);
            String temporaryPassword = generateTemporaryPassword(); // 임시 비밀번호 생성
            EmailSender.sendEmail(email, "임시 비밀번호 발급", "임시 비밀번호: " + temporaryPassword); // 이메일 전송

        } else {
            response.put("success", false);
            response.put("message", "비밀번호 찾기 실패: 해당 사용자 정보가 없습니다.");
        }

        return ResponseEntity.ok(response);
    }

    private String generateTemporaryPassword() {
        // 임시 비밀번호 생성 로직 구현 (예: 랜덤 문자열 생성)
        return "Temp123!"; // 임시로 고정된 값 반환
    }

 /*   @GetMapping("/products")
    public List<Product> getProducts() {

        return memberService.getAllProducts();
    }
*/

    @GetMapping("/products")
    public Map<String, Object> getProducts() {
        Map<String, Object> Rtn = new HashMap<>();

        Rtn.put("ProductList", memberService.getAllProducts());

        return Rtn;
    }

    @GetMapping("/products/{productId}/subproducts")
    public Map<String, Object> getSubProducts(@PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            //List<Map<String, String>> subProducts = memberService.getSubProducts(productId);
            response.put("success", true);
            response.put("subProducts",  memberService.getSubProducts(productId));
            return response;
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to fetch sub-products");
            return response;
        }
    }
}
