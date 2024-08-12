package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.MainMapper;
import com.aptit.octagnosis.modelParm.MainParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private MainMapper MainService;

    @PostMapping("/Main/getDashboard")
    public Map<String, Object> getDashboard(@RequestBody MainParm mainParm) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("List1", MainService.getDashboard1(mainParm));
        Rtn.put("List2", MainService.getDashboard2(mainParm));
        Rtn.put("List3", MainService.getDashboard3(mainParm));
        Rtn.put("List4", MainService.getDashboard4(mainParm));
        return Rtn;
    }

}