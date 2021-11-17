package com.appgros.server.domain.product;

import com.appgros.server.domain.album.Album;
import com.appgros.server.domain.category.Category;
import com.appgros.server.domain.item.Item;
import com.appgros.server.domain.rating.Rate;
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
@NamedQueries( {
        @NamedQuery(name = "Product.findAll", query="select p from Product p"),
        @NamedQuery(name = "Product.findAllInCategory", query="select p from Product p where p.category.id = :categoryId")
} )
@Table(name = "T_PRODUCT")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "description", length = 255)
    private String description;
    @ManyToOne (fetch =FetchType.EAGER)
    @JoinColumn(name ="category_fk", nullable = false)
    private Category category;
    @OneToMany(mappedBy ="product", fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Item> items;
    @OneToMany(mappedBy ="product", fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Rate> rates;
    @OneToMany(mappedBy ="product", fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Album> albums;

}
