package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Org {
    @Id
    private Long orgId;
    private String bizNum;
    private String corpNum;
    private String ceoNm;
    private String zip;
    private String lotNumAddr1;
    private String lotNumAddr2;
    private String stretAddr1;
    private String stretAddr2;
    private String compyNm;
    private String tel1;
    private String tel2;
    private String fax;
    private String bizType;
    private String bizSectr;
    private String mngerNm1;
    private String mngerPhone1;
    private String mngerEmail1;
    private String mngerNm2;
    private String mngerPhone2;
    private String mngerEmail2;
    private String billEmail;
    private String mngerTeam1;
    private String mngerPosit1;
    private String mngerTeam2;
    private String mngerPosit2;
    private String urlCd;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
