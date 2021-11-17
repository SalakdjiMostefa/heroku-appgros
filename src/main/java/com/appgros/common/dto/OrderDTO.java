package com.appgros.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
public final class OrderDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Long id;
    private String orderDate;
      private Collection orderLines;
    private Long customerId;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderDTO() {
    }




}
