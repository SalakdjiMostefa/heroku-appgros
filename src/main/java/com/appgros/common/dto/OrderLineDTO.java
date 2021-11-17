package com.appgros.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an Order Line. This class only
 * transfers data from a distant service to a client.
 */
@Getter
@Setter
public final class OrderLineDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private int    quantity;
    private double unitCost;
    private Long itemId;
    private String itemName;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderLineDTO() {
    }

    public OrderLineDTO(final int quantity, final double unitCost) {
        setQuantity(quantity);
        setUnitCost(unitCost);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderLineDTO{");
        sb.append("quantity=").append(quantity);
        sb.append(", unitCost=").append(unitCost);
        sb.append(", itemId=").append(itemId);
        sb.append(", itemName='").append(itemName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
