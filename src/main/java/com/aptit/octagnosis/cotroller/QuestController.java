package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.AcuntLogMapper;
import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.MngrLogMapper;
import com.aptit.octagnosis.mapper.QuestMapper;
import com.aptit.octagnosis.model.*;
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
public class QuestController {

    @Autowired
    private QuestMapper QuestService;

    Map<String, Object> Rtn = new HashMap<>();
    
    @Autowired
    private CommonLib CommonLib;
    @Autowired
    private ObjectMapper ObjectMapper;
    
    // Test 관련  ************************************************
    
    @PostMapping("/Quest/Test/saveTest")
    public int saveTest(@RequestBody  Map<String, Object> parm) {
        
        Test Test = ObjectMapper.convertValue(parm.get("Test"), Test.class);
        QuestParm QuestParm = ObjectMapper.convertValue(parm.get("QuestParm"), QuestParm.class);
        
        if (QuestParm.getProcType().equals("C")) {
            return QuestService.cretTest(Test);
        } else {
            return QuestService.editTest(Test);
        }
    }
    
    @PostMapping("/Quest/Test/delTest")
    public int delTest(@RequestBody QuestParm parm) {
        return QuestService.delTest(parm);
    }
    
    @PostMapping("/Quest/Test/getTestList")
    public Map<String, Object> getTestList(@RequestBody  Map<String, Object> parm) {
        Rtn.put("TestList", QuestService.getTestList());
        return Rtn;
    }

    // QuestPage 관련  *******************************************
    
    @PostMapping("/Quest/QuestPage/saveQuestPage")
    public int saveQuestPage(@RequestBody  Map<String, Object> parm) {
        
        QuestPage QuestPage = ObjectMapper.convertValue(parm.get("QuestPage"), QuestPage.class);
        QuestParm QuestParm = ObjectMapper.convertValue(parm.get("QuestParm"), QuestParm.class);
        
        if (QuestParm.getProcType().equals("C")) {
            return QuestService.cretQuestPage(QuestPage);
        } else {
            return QuestService.editQuestPage(QuestPage);
        }
    }
    @PostMapping("/Quest/QuestPage/delQuestPage")
    public int delQuestPage(@RequestBody QuestParm parm) {
        return QuestService.delQuestPage(parm);
    }
    
    @PostMapping("/Quest/QuestPage/getQuestPage")
    public QuestPage getQuestPage(@RequestBody  QuestParm parm) {
        return QuestService.getQuestPage(parm);
    }
    
    @PostMapping("/Quest/QuestPage/getQuestPageList")
    public Map<String, Object> getQuestPageList(@RequestBody  QuestParm parm) {
        Rtn.put("QuestPageList", QuestService.getQuestPageList(parm));
        return Rtn;
    }
    
    
}