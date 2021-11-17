package com.appgros.common.dto;

import lombok.Data;

@Data
public class CategoryDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
    private String name;
    private String description;

    // ======================================
    // =            Constructors            =
    // ======================================


}
