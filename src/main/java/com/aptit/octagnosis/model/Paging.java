package com.aptit.octagnosis.model;

import lombok.Data;

@Data

public class Paging {
    private int page;
    private int block;
    private int startRow;
    private int rowCntInPage;
    private String sort;
    private String order;
}
