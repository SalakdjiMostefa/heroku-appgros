package com.appgros.common.dto;

import lombok.Data;

@Data

public class StockDTO implements DataTransfertObject {

    private long id;
    private long itemId;
    private int available;
    private int reserved;

}
