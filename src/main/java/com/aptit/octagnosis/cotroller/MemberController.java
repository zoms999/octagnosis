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

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberMapper memberService;

    @Autowired
    private AcuntMapper AcuntService;

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

}
