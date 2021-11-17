package com.appgros.server.domain.stock;

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
@Table(name = "T_PRODUCT")
public class Stock implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name ="item_fk", nullable = false)
    private Item item;
    @Column(name = "available", length = 255)
    private int available;
    @Column(name = "reserved", length = 255)
    private int reserved;

    public Stock(Item item, int available, int reserved) {
        this.item = item;
        this.available = available;
        this.reserved = reserved;
    }
}
