package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.OrgMapper;
import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.OrgParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;
import com.aptit.octagnosis.common.CommonLib;
@RestController
@RequestMapping("/api")
public class OrgController {

    @Autowired
    private OrgMapper OrgService;

    @Autowired
    private CommonLib Lib;
    
    Map<String, Object> Rtn = new HashMap<>();
    
    
    @PostMapping("/Org/CretOrg")
    public int  CretOrg(@RequestBody Org org) {
        
        return OrgService.CretOrg(org);
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
        Rtn.put("OrgList" , OrgService.GetOrgList(orgParm));

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