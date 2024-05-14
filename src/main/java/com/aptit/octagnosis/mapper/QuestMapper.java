package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface QuestMapper {
    int cretTest(Test test);
    int editTest(Test test);
    int delTest(QuestParm parm);
    List<Map<String, String>> getTestList();
    
    int cretQuestPage(QuestPage questPage);
    int editQuestPage(QuestPage questPage);
    int delQuestPage(QuestParm parm);
    QuestPage getQuestPage(QuestParm parm);
    ArrayList<QuestPageV1> getQuestPageList(QuestParm parm);
    
    List<Map<String, String>> getQuestAttrList();

    int cretQuest(Quest quest);
    int editQuest(Quest quest);
    Quest getQuest(QuestParm parm);
    List<QuestV1> getQuestList(QuestParm parm);
    
    
    int cretQuestItem(QuestItem questItem);
    int editQuestItem(QuestItem questItem);
    int delQuestItem(QuestParm questParm);
    
    long getQuestItemId();
    List<QuestItem> getQuestItemList(QuestParm parm);
    

    int cretQuestImg(QuestImg questItem);
    int editQuestImg(QuestImg questItem);
    int delQuestImg(QuestParm questParm);
    
    long getQuestImgId();
    List<QuestImg> getQuestImgList(QuestParm parm);
    
    
    
}
