package com.appgros.server.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {

    Collection findAllInProduct(Long productId);

    Collection search(String keyword);
}
