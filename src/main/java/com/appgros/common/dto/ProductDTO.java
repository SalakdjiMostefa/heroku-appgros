package com.appgros.common.dto;


import lombok.Data;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of a Product. This class only
 * transfers data from a distant service to a client.
 */

@Data
public final class ProductDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
    private String name;
    private String description;
    private CategoryDTO categoryDTO;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ProductDTO() {
    }

}
