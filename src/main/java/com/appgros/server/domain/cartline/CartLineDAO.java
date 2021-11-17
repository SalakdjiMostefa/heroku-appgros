package com.appgros.server.domain.cartline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartLineDAO extends JpaRepository<CartLine, Long> {

}
