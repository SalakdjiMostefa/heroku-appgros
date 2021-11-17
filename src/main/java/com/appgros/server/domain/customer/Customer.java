package com.appgros.server.domain.customer;

import com.appgros.common.exception.CheckException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
@Table(name = "T_CUSTOMER")
public final class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;
    @Column(name = "telephone", nullable = false, length = 50)
    private String telephone;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "street1", nullable = false, length = 50)
    private String street1;
    @Column(name = "street2", nullable = false, length = 50)
    private String street2;
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @Column(name = "state", nullable = false, length = 50)
    private String state;
    @Column(name = "zipcode", nullable = false, length = 50)
    private String zipcode;
    @Column(name = "password", nullable = false, length = 50)
    private String password;


    public void checkData() throws CheckException {
        if (this.getFirstname() != null && !"".equals(this.getFirstname())) {
            if (this.getLastname() == null || "".equals(this.getLastname())) {
                throw new CheckException("Invalid customer last name");
            }
        } else {
            throw new CheckException("Invalid customer first name");
        }
    }

}
