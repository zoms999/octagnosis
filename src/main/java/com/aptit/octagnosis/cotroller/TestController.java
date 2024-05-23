package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.AcuntLogMapper;
import com.aptit.octagnosis.mapper.AcuntMapper;
import com.aptit.octagnosis.mapper.MngrLogMapper;
import com.aptit.octagnosis.mapper.QuestMapper;
import com.aptit.octagnosis.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/api")
public class QuestController {

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
        Map<String, Object> Rtn = new HashMap<>();

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
        
        List<QuestPageV1> QuestPageV1List = QuestService.getQuestPageList(parm);

        for(QuestPageV1 questPage : QuestPageV1List) {
            parm.setQuestPageId(questPage.questPageId);
            questPage.questList = QuestService.getQuestList(parm);
        }
        
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("QuestPageList", QuestPageV1List);
        return Rtn;
    }
    
    // Quest 관련  *******************************************
    
    @PostMapping("/Quest/Quest/getQuest")
    public Map<String, Object> getQuest(@RequestBody  QuestParm parm) {
        Map<String, Object> Rtn = new HashMap<>();

        Rtn.put("Quest", QuestService.getQuest(parm));
        Rtn.put("QuestPageList", QuestService.getQuestPageList(parm));
        Rtn.put("QuestAttrList", QuestService.getQuestAttrList());
        Rtn.put("QuestItemList", QuestService.getQuestItemList(parm));
        Rtn.put("QuestImgList", QuestService.getQuestImgList(parm));
        return Rtn;
    }
    
    @PostMapping("/Quest/Quest/saveQuest")
    public int saveQuest(@RequestBody  Map<String, Object> parm) {
        
        Quest Quest  = ObjectMapper.convertValue(parm.get("Quest"), Quest.class);
        QuestParm QuestParm = ObjectMapper.convertValue(parm.get("QuestParm"), QuestParm.class);
        
        if (QuestParm.getProcType().equals("C")) {
            return QuestService.cretQuest(Quest);
        } else {
            return QuestService.editQuest(Quest);
        }
    }
    
    @PostMapping("/Quest/Quest/getQuestList")
    public Map<String, Object> getQuestList(@RequestBody  QuestParm parm) {
        Map<String, Object> Rtn = new HashMap<>();

        Rtn.put("QuestId", parm.getQuestPageId());
        Rtn.put("OldQuestId", parm.getOldQuestPageId());

        // 문항의 검사지가 변경 후  검사지 
        Rtn.put("QuestList", QuestService.getQuestList(parm));
        
        // 문항의 검사지가 변경 전 검사지
        parm.setQuestPageId(parm.getOldQuestPageId());
        Rtn.put("OldQuestList", QuestService.getQuestList(parm));
        return Rtn;
    }
    
    // QuestItem 관련  *******************************************
    
    @PostMapping("/Quest/Quest/saveQuestItem")
    public int saveQuestItem(@RequestParam(value = "files", required = false) MultipartFile file
        , @RequestParam("QuestItem") String QuestItemJson
        , @RequestParam("QuestParm") String QuestParmJson) throws JsonProcessingException {
        
        QuestItem QuestItem = ObjectMapper.readValue(QuestItemJson, QuestItem.class);
        QuestParm QuestParm = ObjectMapper.readValue(QuestParmJson, QuestParm.class);

        long QuestId = QuestItem.getQuestId();
        long ItemId = 0;
        if (QuestParm.getProcType().equals("C")) {
            ItemId = QuestService.getQuestItemId();
            QuestItem.setItemId(ItemId);
        } else {
            ItemId = QuestItem.getItemId();
        }
        
        if (file != null && !file.isEmpty()) {
            try {
                
                // 파일 저장 경로가 없으면 생성
                File dir = new File(ItemImgPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                // 파일 저장
                String attachFileNm = file.getOriginalFilename();
                String fileNm =   "QuestItem_" + QuestId + "_" + ItemId + attachFileNm.substring(attachFileNm.lastIndexOf("."));
                String filePath = ItemImgPath + fileNm;
                File dest = new File(filePath);
                file.transferTo(dest);
                
                QuestItem.setImgNm(fileNm);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (QuestParm.getProcType().equals("C")) {
            return QuestService.cretQuestItem(QuestItem);
        } else {
            return QuestService.editQuestItem(QuestItem);
        }
    }
    
    @PostMapping("/Quest/Quest/delQuestItem")
    public int delQuestItem(@RequestBody QuestParm parm) {
        return QuestService.delQuestItem(parm);
    }
    
    
    @PostMapping("/Quest/Quest/getQuestItemList")
    public Map<String, Object> getQuestItemList(@RequestBody  QuestParm parm) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("QuestItemList", QuestService.getQuestItemList(parm));
        return Rtn;
    }
    
    // QuestImg 관련  *******************************************
    
    @PostMapping("/Quest/Quest/saveQuestImg")
    public int saveQuestImg(@RequestParam(value = "files", required = false) MultipartFile file
        , @RequestParam("QuestImg") String QuestImgJson
        , @RequestParam("QuestParm") String QuestParmJson) throws JsonProcessingException {
        
        QuestImg QuestImg = ObjectMapper.readValue(QuestImgJson, QuestImg.class);
        QuestParm QuestParm = ObjectMapper.readValue(QuestParmJson, QuestParm.class);
        
        long QuestId = QuestImg.getQuestId();
        long ImgId = 0;
        if (QuestParm.getProcType().equals("C")) {
            ImgId = QuestService.getQuestImgId();
            QuestImg.setImgId(ImgId);
        } else {
            ImgId = QuestImg.getImgId();
        }
        
        if (file != null && !file.isEmpty()) {
            try {
                
                // 파일 저장 경로가 없으면 생성
                File dir = new File(ImgImgPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                // 파일 저장
                String attachFileNm = file.getOriginalFilename();
                String fileNm =   "QuestImg_" + QuestId + "_" + ImgId + attachFileNm.substring(attachFileNm.lastIndexOf("."));
                String filePath = ImgImgPath + fileNm;
                File dest = new File(filePath);
                file.transferTo(dest);
                
                QuestImg.setImgNm(fileNm);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (QuestParm.getProcType().equals("C")) {
            return QuestService.cretQuestImg(QuestImg);
        } else {
            return QuestService.editQuestImg(QuestImg);
        }
    }
    
    @PostMapping("/Quest/Quest/delQuestImg")
    public int delQuestImg(@RequestBody QuestParm parm) {
        return QuestService.delQuestImg(parm);
    }
    
    
    @PostMapping("/Quest/Quest/getQuestImgList")
    public Map<String, Object> getQuestImgList(@RequestBody  QuestParm parm) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("QuestImgList", QuestService.getQuestImgList(parm));
        return Rtn;
    }
    
}