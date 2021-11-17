package com.appgros.common.dto;


import lombok.Data;


@Data
public final class CustomerDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private long id;
    private String firstname;
    private String lastname;
    private String telephone;
    private String email;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private String password;

    // ======================================
    // =            Constructors            =
    // ======================================

}
