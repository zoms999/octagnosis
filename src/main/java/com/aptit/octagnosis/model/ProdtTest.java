package com.aptit.octagnosis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProdtTest {
    @Id
    private Long    prodtId;
    private Long    testId;
    private int     seq;
    private Long    insId;
    private String  insDt;
    private Long    uptId;
    private String  uptDt;
}
