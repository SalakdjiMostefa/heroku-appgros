package com.appgros.server.domain.album;

import com.appgros.server.domain.picture.Picture;
import com.appgros.server.domain.product.Product;
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
@NamedQuery(name = "Album.findAll", query = "select a from Album a")
@Table(name = "T_ALBUM")

public class Album implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Picture> pictures;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_fk", nullable = false)
    private Product product;

}
