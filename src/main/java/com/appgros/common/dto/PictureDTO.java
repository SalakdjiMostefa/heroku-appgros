package com.appgros.common.dto;

import lombok.Data;

@Data
public class PictureDTO implements DataTransfertObject {

    private long id;
    private String name;
    private String url;
    private long albumId;
}
