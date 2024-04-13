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


    @PostMapping("/OrgTurn/CretOrgTurn")
    public int CretOrgTurn(@RequestBody OrgTurn orgTurn) {

        OrgTurn OrgTurn = OrgTurnService.GetOrgMaxTurn(orgTurn);

        if (OrgTurn == null) {
            orgTurn.setTurnNum(1l);
        } else {
            orgTurn.setTurnNum(OrgTurn.getTurnNum() + 1);
        }

        return OrgTurnService.CretOrgTurn(orgTurn);
    }

    @PostMapping("/OrgTurn/EditOrgTurnUse")
    public int EditOrgTurn(@RequestBody OrgTurnParm orgTurnParm) {
        return OrgTurnService.EditOrgTurnUse(orgTurnParm);
        //return mngrId;
    }

    @PostMapping("/OrgTurn/ChkTurnConnCd")
    public Map<String, Object> GetOrgTurnExistYn(@RequestBody OrgTurnParm orgTurnParm) {
        Map<String, Object> Rtn = new HashMap<>();
        OrgTurn OrgTurn = OrgTurnService.GetExistTurnConnCd(orgTurnParm);

        Rtn.put("ExistYn", (OrgTurn == null ? "N" : "Y"));
        return Rtn;
    }

    // 기관회차정보
    @PostMapping("/OrgTurn/GetOrgTurnList")
    public Map<String, Object> GetOrgTurnList(@RequestBody OrgTurnParm orgTurnParm) {
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("OrgTurnList", OrgTurnService.GetOrgTurnList(orgTurnParm));
        return Rtn;
    }

    // 기관회차 개인정보
    @PostMapping("/OrgTurn/GetOrgTurnPersnList")
    public Map<String, Object> GetOrgTurnPersnList(@RequestBody OrgTurnParm orgTurnParm) {
        Map<String, Object> Rtn = new HashMap<>();
        Rtn.put("TotCnt", OrgTurnService.GetOrgTurnPersnTotCnt(orgTurnParm));
        Rtn.put("List", OrgTurnService.GetOrgTurnPersnList(orgTurnParm));
        return Rtn;

    }


}