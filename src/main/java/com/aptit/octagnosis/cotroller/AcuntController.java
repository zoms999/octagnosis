package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.AcuntLogMapper;
import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.MngrLogMapper;
import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.AcuntParm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AcuntController {

    @Autowired
    private AcuntMapper AcuntService;
    @Autowired
    private MngrLogMapper MngrLogService;
    @Autowired
    private AcuntLogMapper AcuntLogService;

    Map<String, Object> Rtn = new HashMap<>();

    @PostMapping("/Acunt/cretAcunt")
    public int cretAcunt(@RequestBody Acunt org) {

        return AcuntService.cretAcunt(org);
    }

    @PostMapping("/Acunt/editAcunt")
    public int editAcunt(@RequestBody Acunt org) {
        return AcuntService.editAcunt(org);
        //return mngrId;
    }

    @PostMapping("/Acunt/getAcunt")
    public Map<String, Object> getAcunt(@RequestBody Acunt org) {
        Rtn.put("Acunt", AcuntService.getAcunt(org.getAcuntId()));
        return Rtn;
    }

    @PostMapping("/Acunt/getAcuntList")
    public Map<String, Object> getAcuntList(@RequestBody AcuntParm orgParm) {
        //public ResponseEntity<Map<String, Object>> GetAcuntList(@RequestBody AcuntParm orgParm) {

        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("AcuntTotCnt", AcuntService.getAcuntListTotCnt(orgParm));
        Rtn.put("AcuntList", AcuntService.getAcuntList(orgParm));

        //return new ResponseEntity<>(Rtn, Lib.getHeader(), HttpStatus.OK);

        return Rtn;
    }

    @PostMapping("/Acunt/chkAcuntId")
    public Map<String, Object> chkAcuntId(@RequestBody AcuntParm org) {
        Acunt Acunt = AcuntService.getExistAcunt(org);

        Rtn.put("ExistYn", (Acunt == null ? "N" : "Y"));
        return Rtn;
    }

    @PostMapping("/Acunt/chgExpirDt")
    @Transactional
    public int chgExpirDt(@RequestBody AcuntParm acuntParm) {
        int Rtn = 0;

        // 사용기한  수정
        Rtn += AcuntService.editExpirDt(acuntParm);

        // 기관변경이력 기록
        Rtn += AcuntLogService.cretAcuntLog(acuntParm.getAcuntLog());

        return Rtn;
    }

    @PostMapping("/Acunt/chgPw")
    @Transactional
    public int chgPw(@RequestBody AcuntParm acuntParm) {
        int Rtn = 0;

        // 사용기한  수정
        Rtn += AcuntService.editPw(acuntParm);

        // 기관변경이력 기록
        Rtn += AcuntLogService.cretAcuntLog(acuntParm.getAcuntLog());

        return Rtn;
    }


}