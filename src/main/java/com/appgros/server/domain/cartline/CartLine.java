package com.appgros.server.domain.cartline;

import com.appgros.server.domain.cart.Cart;
import com.appgros.server.domain.item.Item;
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
@Table(name = "T_CART")

public class CartLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity", nullable = false, length = 50)
    private int quantity;
    @Column(name = "unitcost", length = 255)
    private double unitCost;
    @Column(name = "totalcartline", length = 255)
    private long totalCartLine;
    @JoinColumn(name = "item_fk", nullable = false)
    private Item item;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_fk", nullable = false)
    private Cart cart;
}
