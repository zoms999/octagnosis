package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.AcuntLogMapper;
import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.CompyMapper;
import com.aptit.octagnosis.mapper.MngrLogMapper;
import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.AcuntParm;
import com.aptit.octagnosis.model.Compy;
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
public class CompyController {
    
    
    @Autowired
    private CompyMapper CompyService;
    
    
    Map<String, Object> Rtn = new HashMap<>();
    
    @PostMapping("/Compy/cretCompy")
    public int cretCompy(@RequestBody Compy compy) {
        
        return CompyService.cretCompy(compy);
    }
    
    @PostMapping("/Compy/editCompy")
    public int editCompy(@RequestBody Compy compy) {
        return CompyService.editCompy(compy);
    }
    
    @PostMapping("/Compy/getCompy")
    public Map<String, Object> getCompy(@RequestBody Compy compy) {
        Rtn.put("Compy", CompyService.getCompy(compy.getBizNum()));
        return Rtn;
    }
    
    
    
}