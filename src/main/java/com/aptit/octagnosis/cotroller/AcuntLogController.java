package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.AcuntLogMapper;
import com.aptit.octagnosis.mapper.AcuntLoginLogMapper;
import com.aptit.octagnosis.model.AcuntLog;
import com.aptit.octagnosis.model.AcuntLogParm;
import com.aptit.octagnosis.model.AcuntLoginLogParm;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AcuntLogController {

    @Autowired
    private AcuntLogMapper AcuntLogService;
    @Autowired
    private AcuntLoginLogMapper AcuntLoginLogService;

    @Autowired
    private CommonLib CommonLib;
    @Autowired
    private ObjectMapper ObjectMapper;

    @PostMapping("/MngrLog/cretAcuntLog")
    @Transactional
    public int cretAcuntLog(@RequestBody Map<String, Object> parm) {

        int Rtn = 0;

        // "Org" 키로 전달된 값을 Org 객체로 변환
        AcuntLog AcuntLog = ObjectMapper.convertValue(parm.get("AcuntLog"), AcuntLog.class);

        AcuntLog.setActinDt(CommonLib.getDateStr("YMDHMS"));
        AcuntLog.setLogId(AcuntLogService.getLogId());

        Rtn = AcuntLogService.cretAcuntLog(AcuntLog);

        return Rtn;
    }

    // 계정로그 목록
    @PostMapping("/AcuntLog/getAcuntLogList")
    public Map<String, Object> getAcuntLogList(@RequestBody AcuntLogParm acuntLogParm) {
        Map<String, Object> Rtn = new HashMap<>();

        Rtn.put("TotCnt", AcuntLogService.getAcuntLogListTotCnt(acuntLogParm));
        Rtn.put("List", AcuntLogService.getAcuntLogList(acuntLogParm));
        return Rtn;

    }

    // 계정 Login 로그 목록
    @PostMapping("/AcuntLoginLog/getAcuntLoginLogList")
    public Map<String, Object> getAcuntLoginLogList(@RequestBody AcuntLoginLogParm acuntLoginLogParm) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("TotCnt", AcuntLoginLogService.getAcuntLoginLogListTotCnt(acuntLoginLogParm));
        Rtn.put("List", AcuntLoginLogService.getAcuntLoginLogList(acuntLoginLogParm));
        return Rtn;

    }
}
