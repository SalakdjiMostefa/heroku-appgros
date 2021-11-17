package com.appgros.common.dto;

import lombok.Data;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an Item. This class only
 * transfers data from a distant service to a client.
 */
@Data
public final class ItemDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private long id;
    private String name;
    private double unitCost;
    private String imagePath;
    private ProductDTO productDTO;
    private StockDTO stockDTO;
    private int qty;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ItemDTO() {
    }

}
