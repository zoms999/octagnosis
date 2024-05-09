package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.QuestPage;
import com.aptit.octagnosis.model.QuestParm;
import com.aptit.octagnosis.model.Test;
import org.apache.ibatis.annotations.Mapper;

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
    
    List<Map<String, String>> getQuestPageList(QuestParm parm);
    
    
    
    //long getLogId();

    //int getAcuntLogListTotCnt(AcuntLogParm acuntLogParm);

    

}
