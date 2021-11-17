package com.appgros.server.domain.album;

import com.appgros.server.domain.picture.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * This class does all the database access for the class Category.
 *
 * @see
 */

@Repository
public interface AlbumDAO extends JpaRepository<Picture,Long> {

}
