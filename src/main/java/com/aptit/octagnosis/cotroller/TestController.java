package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.QuestMapper;
import com.aptit.octagnosis.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private QuestMapper QuestService;

    @Autowired
    private CommonLib CommonLib;
    @Autowired
    private ObjectMapper ObjectMapper;
    
    @Value("${app.quest.itemImgPath}")
    private String ItemImgPath;
    
    @Value("${app.quest.imgImgPath}")
    private String ImgImgPath;
    
    
    @PostMapping("/Test/getQuestPageForTest")
    public Map<String, Object> getQuestPageForTest(@RequestBody  QuestParm parm) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("TestList", QuestService.getTestList());
        Rtn.put("QuestPage", QuestService.getQuestPage(parm));
        Rtn.put("QuestList", QuestService.getQuestList(parm));
        Rtn.put("QuestItemList", QuestService.getQuestItemAllList(parm));
        Rtn.put("QuestImgList", QuestService.getQuestImgAllList(parm));
        
        return Rtn;
    }
    
    
    
}