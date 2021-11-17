package com.appgros.server.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * This class does all the database access for the class Category.
 *
 * @see Category
 */

@Repository
public interface CategoryDAO extends JpaRepository<Category,Long> {

}
