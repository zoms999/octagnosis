package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.OrgTurnMapper;
import com.aptit.octagnosis.model.OrgTurn;
import com.aptit.octagnosis.model.OrgTurnParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrgTurnController {
    
    @Autowired
    private OrgTurnMapper OrgTurnService;
    
    Map<String, Object> Rtn = new HashMap<>();
    
    @PostMapping("/OrgTurn/CretOrgTurn")
    public int  CretOrgTurn(@RequestBody OrgTurn orgTurnParm) {
        
        return OrgTurnService.CretOrgTurn(orgTurnParm);
    }
    
    @PostMapping("/OrgTurn/UptOrgTurn")
    public int EditOrgTurn(@RequestBody OrgTurn orgTurnParm) {
        return OrgTurnService.EditOrgTurn(orgTurnParm);
        //return mngrId;
    }
    
    @PostMapping("/OrgTurn/ChkTurnConnCd")
    public Map<String, Object>  GetOrgTurnExistYn(@RequestBody OrgTurnParm orgTurnParm) {
        OrgTurn OrgTurn = OrgTurnService.GetExistTurnConnCd(orgTurnParm);
        
        Rtn.put("ExistYn", (OrgTurn == null ? "N": "Y"));
        return Rtn;
    }
    
    
}