package com.appgros.common.dto;


import lombok.Data;


@Data
public final class UserDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
     private String password;
     private String email;
     private String street1;
     private String street2;
     private String city;
     private String state;
     private String zipcode;


    // ======================================
    // =            Constructors            =
    // ======================================
    public UserDTO() {
    }



}
