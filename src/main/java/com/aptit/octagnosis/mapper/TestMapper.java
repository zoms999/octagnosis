package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.*;
import com.aptit.octagnosis.modelParm.OrgTurnParm;
import com.aptit.octagnosis.modelParm.QuestParm;
import com.aptit.octagnosis.modelParm.TestParm;
import com.aptit.octagnosis.modelview.QuestPageV1;
import com.aptit.octagnosis.modelview.QuestV1;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {


    int cretAnsPrgrs(AnsPrgrs ansPrgrs);
    int editAnsPrgrs(TestParm testParm);
    int editAnsPrgrsComplete(AnsPrgrs ansPrgrs);

    int cretAns(Ans ans);
    
    // 검사결과 집계
    int cretRsltSumImg(TestParm testParm);
    int cretRsltSumTnk(TestParm testParm);
    int cretRsltSumTal(TestParm testParm);
    int cretRsltSumTnd(TestParm testParm);
    int cretRsltMain(TestParm testParm);
    
    int cretRsltJobTnd(TestParm testParm);
    int cretRsltJobTal(TestParm testParm);
    int cretRsltJobImg(TestParm testParm);
    int cretRsltJobBest(TestParm testParm);
    
    int cretRsltDuty(TestParm testParm);
    
    
    int editRsltMainThink(TestParm testParm);
    int editRsltMainTalnt(TestParm testParm);
    int editRsltMainImg(TestParm testParm);
    

    int delAns(Ans ans);

    int cretOrgTurnPersn(OrgTurnPersn orgTurnPersn);
    int editOrgTurnPersnComplete(OrgTurnPersn orgTurnPersn);

    List<Map<String, String>> getTestList(TestParm parm);
    
    List<Map<String, String>> getTestListForTurn(TestParm parm);

    ProdtTest getFirstTest(TestParm parm);
    ProdtTest getNextTest(TestParm parm);
    QuestPage getNextQuestPage(TestParm parm);

    QuestItem getQuestItem(QuestV1 parm);

    AnsPrgrs getAnsPrgrs(TestParm parm);
    AnsPrgrs getAnsPrgrsForValid(TestParm parm);

    long getAnsPrgrsId();
    
    int getTestRsltTotCnt(TestParm orgTurnParm);
    
    List<Map<String, String>> getTestRsltList(TestParm orgTurnParm);
    

}

