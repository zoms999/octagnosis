package com.aptit.octagnosis.mapper;


import com.aptit.octagnosis.model.*;
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

    List<Map<String, String>> getTestList(TestParm parm);
    
    List<Map<String, String>> getTestListForTurn(TestParm parmW);
    
    ProdtTest getNextTest(TestParm parm);
    QuestPage getNextQuestPage(TestParm parm);
}
