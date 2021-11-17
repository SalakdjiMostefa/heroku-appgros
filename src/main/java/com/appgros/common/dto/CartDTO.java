package com.appgros.common.dto;

import lombok.Data;

import java.util.List;


@Data
public final class CartDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================

    private long id;
    private String CartDate;
    private List<CartLineDTO> cartLineDTOS;
    private long customerId;

    // ======================================
    // =            Constructors            =
    // ======================================

}
