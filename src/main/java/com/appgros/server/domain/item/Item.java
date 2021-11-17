package com.appgros.server.domain.item;

import com.appgros.common.exception.CheckException;
import com.appgros.server.domain.product.Product;
import com.appgros.server.domain.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class represents an Item in the catalog of the YAPS company.
 * The catalog is divided into categories. Each one divided into products
 * and each product in items.
 */

// TODO final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE (ID LIKE '%" + keyword + "%') OR (NAME LIKE '%" + keyword + "%')";

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "select i from Item i"),
        @NamedQuery(name = "Item.search", query = "select i from Item i where i.name = : keyword"),
        @NamedQuery(name = "Item.findAllInProduct", query = "select i from Item i where i.product.id = :productId")
})
@Table(name = "T_ITEM")
public class Item implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "unitCost", nullable = false)
    private double unitCost;
    @OneToOne
    @JoinColumn(name = "stock_fk", nullable = false)
    private Stock stock;
    @Column(name = "qty", columnDefinition = "integer default 0")
    private int qty;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_fk", nullable = false)
    private Product product;


    // ======================================
    // =           Business methods         =
    // ======================================
    @PrePersist
    @PreUpdate
    public void checkData() throws CheckException {
        if (getName() == null || "".equals(getName()))
            throw new CheckException("Invalid name");
        if (getUnitCost() <= 0)
            throw new CheckException("Invalid unit cost");
        if (getProduct() == null || getProduct().getId() == null || "".equals(getProduct().getId()))
            throw new CheckException("Invalid product");
    }

}
