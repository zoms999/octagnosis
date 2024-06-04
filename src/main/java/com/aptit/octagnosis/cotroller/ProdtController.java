package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.ProdtMapper;
import com.aptit.octagnosis.model.Prodt;
import com.aptit.octagnosis.model.Test;
import com.aptit.octagnosis.modelParm.ProdtParm;
import com.aptit.octagnosis.modelParm.QuestParm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProdtController {
    
    @Autowired
    private ProdtMapper ProdtService;
    
    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper ObjectMapper;
    
    @PostMapping("/Prodt/saveProdt")
    public int cretProdt(@RequestBody Map<String, Object> parm) {
        Prodt Prodt = ObjectMapper.convertValue(parm.get("Prodt"), Prodt.class);
        ProdtParm ProdtParm = ObjectMapper.convertValue(parm.get("ProdtParm"), ProdtParm.class);
        
        if (ProdtParm.getProcType().equals("C")) {
            return ProdtService.cretProdt(Prodt);
        } else {
            return ProdtService.editProdt(Prodt);
        }
    }
    
    @PostMapping("/Prodt/delProdt")
    public Map<String, Object> delProdt(@RequestBody Prodt prodt) {
        Map<String, Object> Rtn = new HashMap<>();
        
        ProdtService.delProdt(prodt.getProdtId());
        
        return Rtn;
    }

    @PostMapping("/Prodt/getProdt")
    public Map<String, Object> getProdt(@RequestBody Prodt prodt) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("Prodt", ProdtService.getProdt(prodt.getProdtId()));
        return Rtn;
    }
    
    @PostMapping("/Prodt/getProdtList")
    public Map<String, Object> getProdtList(@RequestBody ProdtParm prodtParm) {
        
        Map<String, Object> Rtn = new HashMap<>();
        //Rtn.put("ProdtTotCnt", ProdtService.getProdtListTotCnt(orgParm));
        Rtn.put("ProdtList", ProdtService.getProdtList(prodtParm));
        
        return Rtn;
    }
    
}