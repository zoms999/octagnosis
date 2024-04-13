package com.aptit.octagnosis.modelview;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PersonalAcuntView {
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
    private String addr1;
    private String addr2;
    private String addr3;
    private String addr4;
    private String educt;
    private String job;
    private String scholNm;
    private String scholMajor;
    private String scholGrade;
    private String jobNm;
    private String jobDuty;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;

    private String acuntId;
    private String expirDt;

}
