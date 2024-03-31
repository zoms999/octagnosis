package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.OrgMapper;
import com.aptit.octagnosis.mapper.OrgTurnMapper;
import com.aptit.octagnosis.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.aptit.octagnosis.common.CommonLib;
@RestController
@RequestMapping("/api")
public class OrgController {

    @Autowired
    private OrgMapper OrgService;
    @Autowired
    private AcuntMapper AcuntService;
    @Autowired
    private OrgTurnMapper OrgTurnService;
    
    @Autowired
    private CommonLib CommonLib;
    @Autowired
    private ObjectMapper ObjectMapper;
    
    Map<String, Object> Rtn = new HashMap<>();
    
    
    @PostMapping("/Org/CretOrg")
    @Transactional
    public int  CretOrg(@RequestBody Map<String, Object> parm) {
        
        int Rtn = 0;
        
        // "Org" 키로 전달된 값을 Org 객체로 변환
        Org Org = ObjectMapper.convertValue(parm.get("Org"), Org.class);
        OrgTurn OrgTurn = ObjectMapper.convertValue(parm.get("OrgTurn"), OrgTurn.class);
        Acunt Acunt = ObjectMapper.convertValue(parm.get("Acunt"), Acunt.class);
        
        String CurDt =  CommonLib.getDateStr("YMD"); //  regDt.
        
        // 기관
        Org.setOrgId(OrgService.GetOrgId());
        
        // 기관회차
        OrgTurn.setOrgId(Org.getOrgId());
        
        // 계정
        Acunt.setUserId(Org.getOrgId());
        Acunt.setRegDt(CurDt);
        
        Rtn += OrgService.CretOrg(Org);
        Rtn += OrgTurnService.CretOrgTurn(OrgTurn);
        Rtn += AcuntService.CretAcunt(Acunt);
        
        return Rtn;
    }

    @PostMapping("/Org/UptOrg")
    public int EditOrg(@RequestBody Org org) {
        return OrgService.EditOrg(org);
        //return mngrId;
    }

    @PostMapping("/Org/GetOrg")
    public Map<String, Object>  GetOrgById(@RequestBody Org org) {
        Rtn.put("Org" , OrgService.GetOrgById(org.getOrgId()));
        return Rtn;
    }
    
    @PostMapping("/Org/GetOrgList")
    public Map<String, Object> GetOrgList(@RequestBody OrgParm orgParm) {
        //public ResponseEntity<Map<String, Object>> GetOrgList(@RequestBody OrgParm orgParm) {
        
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("OrgTotCnt", OrgService.GetOrgListTotCnt(orgParm));

        /*
        List<Map<String, String>> OrgList = OrgService.GetOrgList(orgParm);
        
        OrgTurnParm OrgTurnParm = new OrgTurnParm();
        for (Map<String,  String> Item :OrgList) {
            String orgId = Item.get("OrgId").toString();
            Long Temp = Long.parseLong(orgId);
            OrgTurnParm.setOrgId(Temp);
            OrgTurn OrgTurn = OrgTurnService.GetOrgMaxTurn(OrgTurnParm);

            Item.put("TurnId",  OrgTurn.getTurnId().toString());
            Item.put("InsDt",  OrgTurn.getInsDt());
            Item.put("ExpireDt",  OrgTurn.getTurnId().toString());
            Item.put("TurnReqCnt",  OrgTurn.getTurnReqCnt().toString());
            Item.put("TurnUseCnt",  OrgTurn.getTurnUseCnt().toString());
        }
        */
        
        Rtn.put("OrgList", OrgService.GetOrgList(orgParm));

        //return new ResponseEntity<>(Rtn, Lib.getHeader(), HttpStatus.OK);
        
        return Rtn;
    }
    
    @PostMapping("/Org/ChkUrlCd")
    public Map<String, Object>  GetOrgById(@RequestBody OrgParm org) {
        Org Org = OrgService.GetExistOrg(org);
        
        Rtn.put("ExistYn", (Org == null ? "N": "Y"));
        return Rtn;
    }
    
    
}