package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Manager {
    @Id
    private Long mngrId;
    private String useYn;
    private String email;
    private String pw;
    private String mngrNm;
    private String phone;
    private String tel;
    private String zip;
    private String roadAddr1;
    private String roadAddr2;
    private String stretAddr1;
    private String stretAddr2;
    private int authPersn;
    private int authRsltView;
    private int authAdmin;
    private int authLogView;
    private int authStati;
    private int authBbs;
    private int authOrg;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
