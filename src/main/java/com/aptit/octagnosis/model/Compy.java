package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Compy {
    @Id
    private String bizNum;
    private String compyNm;
    private String tel1;
    private String tel2;
    private String fax;
    private String corpNum;
    private String ceoNm;
    private String zip;
    private String addrStret;
    private String addrLotNum;
    private String addr2;
    private String addr3;
    private String billEmail;
    private String emailTo;
    private String onlineNum;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
