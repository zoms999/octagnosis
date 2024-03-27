package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.OrgMapper;
import com.aptit.octagnosis.model.Manager;
import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.OrgParm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrgController {

    @Autowired
    private OrgMapper OrgService;
    
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
        
        System.out.println("********* Limit : " +  orgParm.getPaging().getLimit());
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("OrgTotCnt", OrgService.GetOrgListTotCnt(orgParm));
        Rtn.put("OrgList" , OrgService.GetOrgList(orgParm));
 
        return Rtn;
    }
}