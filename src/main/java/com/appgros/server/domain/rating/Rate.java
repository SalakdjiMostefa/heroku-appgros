package com.appgros.server.domain.rating;

import com.appgros.server.domain.customer.Customer;
import com.appgros.server.domain.product.Product;
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
@Table(name = "T_RATE")

public class Rate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_fk", nullable = false)
    private Product product;
    @OneToOne
    @JoinColumn(name = "customer_fk", nullable = false)
    private Customer customer;
    @Column(name = "rating", nullable = false, length = 50)
    private int rating;
}
