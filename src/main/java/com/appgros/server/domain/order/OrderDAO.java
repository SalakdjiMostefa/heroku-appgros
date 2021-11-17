package com.appgros.server.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

}
