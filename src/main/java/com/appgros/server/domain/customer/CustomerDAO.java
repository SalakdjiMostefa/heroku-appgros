package com.appgros.server.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * This class does all the database access for the class Category.
 *
 * @see
 */

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Long> {

}
