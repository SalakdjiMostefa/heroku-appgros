package com.appgros.common.dto;

import lombok.Data;


@Data
public final class RateDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================

    private long id;
    private long product_id;
    private long customerId;
    private int rate;
    // ======================================
    // =            Constructors            =
    // ======================================

    public RateDTO() {
    }

}
