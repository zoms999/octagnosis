package com.aptit.octagnosis.model;

import lombok.Data;

@Data

public class Paging {
    private int page;
    private int pageBlock;
    private int limit;
    private String sort;
    private String order;

}
