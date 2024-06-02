package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.common.CommonLib;
import com.aptit.octagnosis.mapper.*;
import com.aptit.octagnosis.model.*;
import com.aptit.octagnosis.modelParm.AcuntParm;
import com.aptit.octagnosis.modelParm.OrgParm;
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
public class OrgController {

    @Autowired
    private OrgMapper OrgService;
    @Autowired
    private AcuntMapper AcuntService;
    @Autowired
    private OrgTurnMapper OrgTurnService;
    @Autowired
    private MngrLogMapper MngrLogService;
    @Autowired
    private AcuntLogMapper AcuntLogService;

    @Autowired
    private CommonLib CommonLib;
    @Autowired
    private ObjectMapper ObjectMapper;

    // 기관등록
    @PostMapping("/Org/cretOrg")
    @Transactional
    public int cretOrg(@RequestBody Map<String, Object> parm) {

        int Rtn = 0;

        // "Org" 키로 전달된 값을 Org 객체로 변환
        Org Org = ObjectMapper.convertValue(parm.get("Org"), Org.class);
        OrgTurn OrgTurn = ObjectMapper.convertValue(parm.get("OrgTurn"), OrgTurn.class);
        Acunt Acunt = ObjectMapper.convertValue(parm.get("Acunt"), Acunt.class);

        String CurDt = CommonLib.getDateStr("YMD"); //  regDt.

        // 기관
        Org.setOrgId(OrgService.getOrgId());

        // 기관회차
        OrgTurn.setOrgId(Org.getOrgId());

        // 계정
        Acunt.setUserId(Org.getOrgId());
        Acunt.setRegDt(CurDt);

        Rtn += OrgService.cretOrg(Org);
        Rtn += OrgTurnService.cretOrgTurn(OrgTurn);
        Rtn += AcuntService.cretAcunt(Acunt);

        return Rtn;
    }

    // 기관수정
    @PostMapping("/Org/editOrg")
    public int editOrg(@RequestBody Org org) {
        return OrgService.editOrg(org);
        //return mngrId;
    }

    // 기관조회 : 수정시
    @PostMapping("/Org/getOrg")
    public Map<String, Object> getOrg(@RequestBody OrgParm orgParm) {
        Map<String, Object> Rtn = new HashMap<>();
        
        Rtn.put("Org", OrgService.getOrgById(orgParm.getOrgId()));
        AcuntParm AcuntParm = new AcuntParm();
        AcuntParm.setUserType("C00101");
        AcuntParm.setUserId(orgParm.getOrgId());
        Rtn.put("Acunt", AcuntService.getAcuntByUserId(AcuntParm));
        return Rtn;
    }

    // 기관목록

    @PostMapping("/Org/getOrgList")
    public Map<String, Object> getOrgList(@RequestBody OrgParm orgParm) {
        //public ResponseEntity<Map<String, Object>> GetOrgList(@RequestBody OrgParm orgParm) {

        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("OrgTotCnt", OrgService.getOrgListTotCnt(orgParm));

        /*
        List<Map<String, String>> OrgList = OrgService.GetOrgList(orgParm);
        
        OrgTurnParm OrgTurnParm = new OrgTurnParm();
        for (Map<String,  String> Item :OrgList) {
            String orgId = Item.get("OrgId").toString();
            Long Temp = Long.parseLong(orgId);
            OrgTurnParm.setOrgId(Temp);
            OrgTurn OrgTurn = OrgTurnService.GetOrgMaxTurn(OrgTurnParm);

            Item.put("TurnId",  OrgTurn.getTurnId().toString());
            Item.put("InsDt",  OrgTurn.getInsDt());
            Item.put("ExpireDt",  OrgTurn.getTurnId().toString());
            Item.put("TurnReqCnt",  OrgTurn.getTurnReqCnt().toString());
            Item.put("TurnUseCnt",  OrgTurn.getTurnUseCnt().toString());
        }
        */

        Rtn.put("OrgList", OrgService.getOrgList(orgParm));

        //return new ResponseEntity<>(Rtn, Lib.getHeader(), HttpStatus.OK);

        return Rtn;
    }

    // 기관코드 유효성 검증
    @PostMapping("/Org/chkUrlCd")
    public Map<String, Object> chkUrlCd(@RequestBody OrgParm org) {
        // 수정시에는 UrlCdNew에 값이 들어옴
        if (org.getUrlCd() == null || org.getUrlCd().isEmpty()) {
            org.setUrlCd(org.getUrlCdNew());
        }
        Org Org = OrgService.getExistOrg(org);
        
        Map<String, Object> Rtn = new HashMap<>();

        Rtn.put("ExistYn", (Org == null ? "N" : "Y"));
        return Rtn;
    }

    // 기관인증코드 수정
    @PostMapping("/Org/chgOrgUrlCd")
    @Transactional
    public int chgOrgUrlCd(@RequestBody OrgParm orgParm) {
        int Rtn = 0;

        // 기관인증코드 수정
        Rtn += OrgService.editUrlCd(orgParm);

        // 기관변경이력 기록
        Rtn += AcuntLogService.cretAcuntLog(orgParm.getAcuntLog());

        return Rtn;
    }


}