package com.appgros.server.domain.picture;

import com.appgros.server.domain.album.Album;
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
@NamedQuery(name = "Picture.findAll", query = "select p from Picture p")
@Table(name = "T_PICTURE")

public class Picture implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "url", length = 255)
    private String url;
    @ManyToOne (fetch =FetchType.EAGER)
    @JoinColumn(name ="album_fk", nullable = false)
    private Album album;
}
