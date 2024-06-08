package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Personal {
    @Id
    private Long persnId;
    private String persnNm;
    private String birthDate;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String sex;
    private String phone;
    private String tel;
    private String email;
    private String zip;
    private String addrStret;
    private String addrLotNum;
    private String addr2;
    private String addr3;
    private String educt;
    private String eductStus;
    private String scholNm;
    private String scholMajor;
    private String scholGrade;
    private String job;
    private String jobNm;
    private String jobDuty;
    private long orgId;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;

}
