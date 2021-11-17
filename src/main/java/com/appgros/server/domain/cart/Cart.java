package com.appgros.server.domain.cart;

import com.appgros.server.domain.cartline.CartLine;
import com.appgros.server.domain.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "T_CART")

public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cartDate", length = 255)
    private String cartDate;
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<CartLine> cartLines;
    @JoinColumn(name = "customer_fk", nullable = false)
    private Customer customer;

}
