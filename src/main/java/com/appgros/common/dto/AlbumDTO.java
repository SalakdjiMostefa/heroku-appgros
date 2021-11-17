package com.appgros.common.dto;

import lombok.Data;

import java.util.Collection;

@Data

public class AlbumDTO implements DataTransfertObject {

    private long id;
    private String name;
    private String description;
    private Collection<PictureDTO> picturesDtos;
    private long productId;

}
