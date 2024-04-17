package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.OrgTurnMapper;
import com.aptit.octagnosis.model.OrgTurn;
import com.aptit.octagnosis.model.OrgTurnParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrgTurnController {

    @Autowired
    private OrgTurnMapper OrgTurnService;


    @PostMapping("/OrgTurn/cretOrgTurn")
    public int cretOrgTurn(@RequestBody OrgTurn orgTurn) {

        OrgTurn OrgTurn = OrgTurnService.getOrgMaxTurn(orgTurn);

        if (OrgTurn == null) {
            orgTurn.setTurnNum(1l);
        } else {
            orgTurn.setTurnNum(OrgTurn.getTurnNum() + 1);
        }

        return OrgTurnService.cretOrgTurn(orgTurn);
    }

    @PostMapping("/OrgTurn/editOrgTurnUse")
    public int editOrgTurnUse(@RequestBody OrgTurnParm orgTurnParm) {
        return OrgTurnService.editOrgTurnUse(orgTurnParm);
        //return mngrId;
    }

    @PostMapping("/OrgTurn/chkTurnConnCd")
    public Map<String, Object> chkTurnConnCd(@RequestBody OrgTurnParm orgTurnParm) {
        Map<String, Object> Rtn = new HashMap<>();
        OrgTurn OrgTurn = OrgTurnService.getExistTurnConnCd(orgTurnParm);

        Rtn.put("ExistYn", (OrgTurn == null ? "N" : "Y"));
        return Rtn;
    }

    // 기관회차정보
    @PostMapping("/OrgTurn/getOrgTurnList")
    public Map<String, Object> getOrgTurnList(@RequestBody OrgTurnParm orgTurnParm) {
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("OrgTurnList", OrgTurnService.getOrgTurnList(orgTurnParm));
        return Rtn;
    }

    // 기관회차 개인정보
    @PostMapping("/OrgTurn/getOrgTurnPersnList")
    public Map<String, Object> getOrgTurnPersnList(@RequestBody OrgTurnParm orgTurnParm) {
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("TotCnt", OrgTurnService.getOrgTurnPersnTotCnt(orgTurnParm));
        Rtn.put("List", OrgTurnService.getOrgTurnPersnList(orgTurnParm));
        return Rtn;

    }


}