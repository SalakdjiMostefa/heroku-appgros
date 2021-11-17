package com.appgros.common.dto;

import lombok.Data;

@Data
public class ShoppingCartItemDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private Long itemId;
    private String itemName;
    private String productDescription;
    private int    quantity;
    private double unitCost;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ShoppingCartItemDTO(final Long itemId, final String itemName, final String productDescription, final int quantity, final double unitCost) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public double getTotalCost() {
        return quantity * unitCost;
    }
}
