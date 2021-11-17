package com.appgros.common.dto;

import lombok.Data;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an Order Line. This class only
 * transfers data from a distant service to a client.
 */
@Data
public final class CartLineDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private int    quantity;
    private double unitCost;
    private long totalCartLine;
    private long itemId;
    private long cartId;

    // ======================================
    // =            Constructors            =
    // ======================================

}
