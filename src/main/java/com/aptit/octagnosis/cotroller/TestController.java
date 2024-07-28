package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.*;
import com.aptit.octagnosis.model.*;
import com.aptit.octagnosis.modelParm.OrgTurnParm;
import com.aptit.octagnosis.modelParm.QuestParm;
import com.aptit.octagnosis.modelParm.TestParm;
import com.aptit.octagnosis.modelview.QuestV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private OrgTurnMapper OrgTurnService;

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
        
        if (parm.getOrgId() == 0) {     // 개인 사용자 검사 목록
            Rtn.put("TestList", TestService.getTestList(parm));
        } else {                        // 기관 사용자 검사 목록
            Rtn.put("TestList", TestService.getTestListForTurn(parm));
        }
        return Rtn;
    }
    
    
    // 다음 검사,검사지 조회
    @PostMapping("/Test/getNextTest")
    @Transactional
    public Map<String, Object> getNextTest(@RequestBody TestParm testParm) {
        Map<String, Object> Rtn = new HashMap<>();
        long ansPrgrsId = testParm.getAnsPrgrsId();
        long testId = testParm.getTestId();
        long questPageId;

        // 답변진행이 없으면 -> 답변진행등록
        if (ansPrgrsId == 0) {

            // AnsPrgsId 를 다시 조회함. -> 답변진행이 등록안된 상태에서 testStart 페이지에서 "검사시작"을 계속 클릭할수 있음.
            AnsPrgrs ansPrgrsVaild  = TestService.getAnsPrgrsForValid(testParm);

            if (ansPrgrsVaild != null) {
                ansPrgrsId = ansPrgrsVaild.getAnsPrgrsId();
                testId = ansPrgrsVaild.getTestId();
            } else {

                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String curDt = today.format(formatter);

                ansPrgrsId = TestService.getAnsPrgrsId();

                AnsPrgrs ansPrgrs = new AnsPrgrs() {{
                    setTestId(0l);
                    setQuestPageId(0l);
                    setStartDt(curDt);
                    setEndDt("");
                    setDoneYn("N");
                    setAcuntId(testParm.getAcuntId());
                    setProdtId(testParm.getProdtId());
                    setTurnId(testParm.getTurnId());
                    setPayId(testParm.getPayId());
                    setInsId(testParm.getInsId());
                }};

                ansPrgrs.setAnsPrgrsId(ansPrgrsId);

                TestService.cretAnsPrgrs(ansPrgrs);

                // 기관회차 등록
                if (testParm.getOrgId() != 0 && testParm.getTurnId() != 0) {
                    OrgTurnPersn orgTurnPersn = new OrgTurnPersn() {{
                        setOrgId(testParm.getOrgId());
                        setTurnId(testParm.getTurnId());
                        setPersnId(testParm.getInsId());
                        setRegDt(curDt);
                        setStartDt(curDt);
                        setEndDt("");
                        setInsId(testParm.getInsId());
                    }};

                    TestService.cretOrgTurnPersn(orgTurnPersn);
                }

                // 구매상품 사용처리
                if (testParm.getPayId() != 0) {


                }
            }
        }

        // testId를 다시 조회함 -> 사용자가 검사창을 닫는 경우 문제를 이어갈수 없기에 다시 testId 를 조회함.
        testParm.setAnsPrgrsId(ansPrgrsId);
        AnsPrgrs ansPrgrs  = TestService.getAnsPrgrsForValid(testParm);
        testParm.setTestId(testId == 0 ? ansPrgrs.getTestId() : testId);
        testParm.setQuestPageId(ansPrgrs.getQuestPageId());

        if (testId == 0) {      // 검사 시작
            // 다음검사 조회
            ProdtTest prodtTest = TestService.getFirstTest(testParm);
            
            testId = prodtTest.getTestId();
            questPageId = 0;
        } else {
            // 다음검사지 조회
            QuestPage questPage = TestService.getNextQuestPage(testParm);
            
            // 다음검사지가 없으면(다음검사로 넘어감.)
            if (questPage == null) {
                ProdtTest prodtTest = TestService.getNextTest(testParm);
                
                testId = prodtTest == null ? 0 : prodtTest.getTestId();
                questPageId = 0;
            } else {
                questPageId = questPage.getQuestPageId();
            }
        }
        
        //parm.setPersnId(persn.getPersnId());

        Rtn.put("ansPrgrsId", ansPrgrsId);
        Rtn.put("testId", testId);
        Rtn.put("questPageId", questPageId);
        return Rtn;
    }

    @PostMapping("/Test/saveAns")
    @Transactional
    public Map<String, Object> saveAns(@RequestBody TestParm testParm) {
        Map<String, Object> Rtn = new HashMap<>();

        // 답변진행 TestId, QuestPageId  수정
        TestService.editAnsPrgrs(testParm);

        for(QuestV1 questV1 : testParm.getQuestList()) {
            Ans ans = new Ans() {{
                setAnsPrgrsId(testParm.getAnsPrgrsId());
                setQuestId(questV1.getQuestId());
                setVal1(questV1.getVal1());
                setVal2(questV1.getVal2());
            }};

            // Val1이 선택한 값.
            questV1.setItemId(Long.parseLong(questV1.getVal1()));

            QuestItem questItem = TestService.getQuestItem(questV1);

            ans.setWeigt(questItem.getWeigt());
            ans.setInsId(testParm.getInsId());

            // 답안 삭제
            TestService.delAns(ans);
            // 답안 등록
            TestService.cretAns(ans);
        }


        // 다음질문지 정보 추출
        long prodtId = testParm.getTestId();
        long testId = testParm.getTestId();
        long questPageId = testParm.getQuestPageId();

        if (testId == 0) {      // 검사 시작
            // 다음검사 조회
            ProdtTest prodtTest = TestService.getNextTest(testParm);

            testId = prodtTest.getTestId();
            questPageId = 0;
        } else {
            // 다음검사지 조회
            QuestPage questPage = TestService.getNextQuestPage(testParm);

            // 다음검사지가 없으면(다음검사로 넘어감.)
            if (questPage == null) {
                ProdtTest prodtTest = TestService.getNextTest(testParm);

                testId = prodtTest == null ? 0 : prodtTest.getTestId();
                questPageId = 0;
            } else {
                questPageId = questPage.getQuestPageId();
            }
        }


        // 검사 완료처리
        if (testId == 0  && questPageId == 0) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String curDt = today.format(formatter);

            AnsPrgrs ansPrgrs = new AnsPrgrs() {{
                setAnsPrgrsId(testParm.getAnsPrgrsId());
                setEndDt(curDt);
                setDoneYn("N");
                setUptId(testParm.getInsId());
            }};

            TestService.editAnsPrgrsComplete(ansPrgrs);

            AnsPrgrs LastAnsPrgrs = TestService.getAnsPrgrs(testParm);

            // 답변에 회차id가 있고, 전달된 파라미터에서 TurnConnCd 가 있으면 기관회차개인 정보 업데이트
            if (LastAnsPrgrs.getTurnId() != 0 && !testParm.getTurnConnCd().isEmpty()) {

                OrgTurnParm orgTurnParm = new OrgTurnParm() {{
                    setTurnConnCd(testParm.getTurnConnCd());
                }};

                OrgTurn orgTurn = OrgTurnService.getExistTurnConnCd(orgTurnParm);

                OrgTurnPersn orgTurnPersn  = new OrgTurnPersn() {{
                    setOrgId(orgTurn.getOrgId());
                    setTurnId(orgTurn.getTurnId());
                    setPersnId(testParm.getInsId());
                    setEndDt(curDt);
                    setUptId(testParm.getInsId());
                }};

                // 기관회차에 등록한 정보 EndDt 정보 등록
                TestService.editOrgTurnPersnComplete(orgTurnPersn);
            }
            
            // 질문지 종합결과 집계  ****************
            // 선호도 집계
            TestService.cretRsltSumImg(testParm);
            // 사고력 집계
            TestService.cretRsltSumTnk(testParm);
            // 사고력 - 재능 집계
            TestService.cretRsltSumTal(testParm);
            // 성향 집계
            TestService.cretRsltSumTnd(testParm);
            
            // 검사 결과 Tedcy 집계
            TestService.cretRsltMain(testParm);
            // 검사 결과 Think 집계
            TestService.editRsltMainThink(testParm);
            // 검사 결과 Talnt 집계
            TestService.editRsltMainTalnt(testParm);
            // 검사 결과 Image 집계
            TestService.editRsltMainImg(testParm);
            
            // 검사 결과 성향 직업 집계
            TestService.cretRsltJobTnd(testParm);
            // 검사 결과 재능 직업 집계
            TestService.cretRsltJobTal(testParm);
            // 검사 결과 선호 직업 집계
            TestService.cretRsltJobImg(testParm);
            // 검사 결과 Best 직업 집계
            TestService.cretRsltJobBest(testParm);
            
            // 검사 결과 직무 집계
            TestService.cretRsltDuty(testParm);
        }

        Rtn.put("testId", testId);
        Rtn.put("questPageId", questPageId);
        return Rtn;
    }


    
    
    
}