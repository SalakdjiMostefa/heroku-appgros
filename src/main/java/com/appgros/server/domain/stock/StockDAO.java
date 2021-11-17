package com.appgros.server.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockDAO extends JpaRepository<Stock, Long> {
//    Collection findAllInCategory(Long categoryId);
}
