package com.appgros.server.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
    Collection findAllInCategory(Long categoryId);
}
