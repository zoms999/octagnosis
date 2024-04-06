package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.MngrLogMapper;
import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.MngrLog;
import com.aptit.octagnosis.model.Org;
import com.aptit.octagnosis.model.OrgTurn;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MngrLogController {
    
    @Autowired
    private MngrLogMapper MngrLogService;
    
    @Autowired
    private CommonLib CommonLib;
    @Autowired
    private ObjectMapper ObjectMapper;
    
    @PostMapping("/MngrLog/CretMngLog")
    @Transactional
    public int  CretMngLog(@RequestBody Map<String, Object> parm) {
        
        int Rtn = 0;
        
        // "Org" 키로 전달된 값을 Org 객체로 변환
        MngrLog MngrLog = ObjectMapper.convertValue(parm.get("MngrLog"), MngrLog.class);
        
        MngrLog.setActinDt(CommonLib.getDateStr("YMDHMS"));
        MngrLog.setLogId(MngrLogService.GetLogId());
        
        Rtn = MngrLogService.CretMngrLog(MngrLog);
        
        return Rtn;
    }
    
    
}
