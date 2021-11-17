package com.appgros.common.dto;

import java.time.LocalDate;
import java.util.Collection;


public final class OrderDTOWithString implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Long id;
    private LocalDate orderDate;
      private Collection orderLines;
    private Long customerId;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderDTOWithString() {
    }




}
