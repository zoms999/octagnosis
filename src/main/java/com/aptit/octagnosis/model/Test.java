package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Test {
    @Id
    private Long testId;
    private String testNm;
    private int seq;
    private Long insId;
    private String insDt;
    private Long uptId;
    private String uptDt;
}
