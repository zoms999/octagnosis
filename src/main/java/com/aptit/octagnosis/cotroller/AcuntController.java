package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
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

    Map<String, Object> Rtn = new HashMap<>();

    @PostMapping("/Acunt/CretAcunt")
    public int  CretAcunt(@RequestBody Acunt org) {

        return AcuntService.CretAcunt(org);
    }

    @PostMapping("/Acunt/UptAcunt")
    public int EditAcunt(@RequestBody Acunt org) {
        return AcuntService.EditAcunt(org);
        //return mngrId;
    }
    
    @PostMapping("/Acunt/GetAcunt")
    public Map<String, Object>  GetAcunt(@RequestBody Acunt org) {
        Rtn.put("Acunt" , AcuntService.GetAcunt(org.getAcuntId()));
        return Rtn;
    }
    
    @PostMapping("/Acunt/GetAcuntList")
    public Map<String, Object> GetAcuntList(@RequestBody AcuntParm orgParm) {
        //public ResponseEntity<Map<String, Object>> GetAcuntList(@RequestBody AcuntParm orgParm) {
        
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("AcuntTotCnt", AcuntService.GetAcuntListTotCnt(orgParm));
        Rtn.put("AcuntList" , AcuntService.GetAcuntList(orgParm));
        
        //return new ResponseEntity<>(Rtn, Lib.getHeader(), HttpStatus.OK);
        
        return Rtn;
    }
    
    @PostMapping("/Acunt/ChkAcuntId")
    public Map<String, Object>  GetAcuntById(@RequestBody AcuntParm org) {
        Acunt Acunt = AcuntService.GetExistAcunt(org);
        
        Rtn.put("ExistYn", (Acunt == null ? "N": "Y"));
        return Rtn;
    }
    @PostMapping("/Acunt/ChgExpirDt")
    @Transactional
    public int  ChgExpirDt(@RequestBody AcuntParm acuntParm) {
        int Rtn = 0;

        // 사용기한  수정
        Rtn += AcuntService.EditExpirDt(acuntParm);

        // 기관변경이력 기록
        Rtn += MngrLogService.CretMngrLog(acuntParm.getMngrLog());

        return Rtn;
    }

    @PostMapping("/Acunt/ChgPw")
    @Transactional
    public int  ChgPw(@RequestBody AcuntParm acuntParm) {
        int Rtn = 0;

        // 사용기한  수정
        Rtn += AcuntService.EditPw(acuntParm);

        // 기관변경이력 기록
        Rtn += MngrLogService.CretMngrLog(acuntParm.getMngrLog());

        return Rtn;
    }


}