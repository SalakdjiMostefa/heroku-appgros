package com.appgros.common.dto;


import lombok.Data;


@Data
public final class UserConnectDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String email;
    private String password;

    // ======================================
    // =            Constructors            =
    // ======================================
    public UserConnectDTO() {
    }



}
