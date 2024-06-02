package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.ProdtMapper;
import com.aptit.octagnosis.model.Prodt;
import com.aptit.octagnosis.modelParm.ProdtParm;
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
    
    @PostMapping("/Prodt/cretProdt")
    public int cretProdt(@RequestBody Prodt prodt) {
        
        return ProdtService.cretProdt(prodt);
    }
    
    @PostMapping("/Prodt/editProdt")
    public int editProdt(@RequestBody Prodt prodt) {
        return ProdtService.editProdt(prodt);
        //return mngrId;
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