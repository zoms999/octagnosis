package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.PersonalMapper;
import com.aptit.octagnosis.mapper.QuestMapper;
import com.aptit.octagnosis.mapper.TestMapper;
import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Personal;
import com.aptit.octagnosis.modelParm.QuestParm;
import com.aptit.octagnosis.modelParm.TestParm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private QuestMapper QuestService;
    
    @Autowired
    private TestMapper TestService;
    
    @Autowired
    private AcuntMapper AcuntService;
    
    @Autowired
    private PersonalMapper PersnService;
    
    @Autowired
    private CommonLib CommonLib;
    @Autowired
    private ObjectMapper ObjectMapper;
    
    @Value("${app.quest.itemImgPath}")
    private String ItemImgPath;
    
    @Value("${app.quest.imgImgPath}")
    private String ImgImgPath;
    
    
    @PostMapping("/Test/getQuestPageForTest")
    public Map<String, Object> getQuestPageForTest(@RequestBody QuestParm parm) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("TestList", QuestService.getTestList());
        Rtn.put("QuestPage", QuestService.getQuestPage(parm));
        Rtn.put("QuestList", QuestService.getQuestList(parm));
        Rtn.put("QuestItemList", QuestService.getQuestItemAllList(parm));
        Rtn.put("QuestImgList", QuestService.getQuestImgAllList(parm));
        
        return Rtn;
    }
    
    @PostMapping("/Test/getTestList")
    public Map<String, Object> getTestList(@RequestBody TestParm parm) {
        Map<String, Object> Rtn = new HashMap<>();

        Acunt acunt = AcuntService.getAcunt(parm.getAcuntId());
        Personal persn =  PersnService.getPersonalById(acunt.getUserId());
        
        parm.setPersnId(persn.getPersnId());
        
        if (parm.getOrgId() == 0) {     // 기관 사용자 검사 목록
            Rtn.put("TestList", TestService.getTestList(parm));
        } else {            // 개인 사용자 검사 목록
            Rtn.put("TestList", TestService.getTestListForTurn(parm));
        }
        return Rtn;
    }
    
    
    
    
    
}