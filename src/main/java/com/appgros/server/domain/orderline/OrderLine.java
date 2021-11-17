package com.appgros.server.domain.orderline;

import com.appgros.server.domain.item.Item;
import com.appgros.server.domain.order.Order;
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
@Table(name = "T_ORDER")

public class OrderLine implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity", nullable = false, length = 50)
    private int quantity;
    @Column(name = "unitcost", length = 255)
    private double unitCost;
    @Column(name = "totalorderline", length = 255)
    private long totalOrderLine;
    @JoinColumn(name = "item_fk", nullable = false)
    private Item item;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_fk", nullable = false)
    private Order order;

}
