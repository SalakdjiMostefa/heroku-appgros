package com.appgros.server.service.order;

import com.appgros.common.dto.CartDTO;
import com.appgros.common.dto.OrderDTO;
import com.appgros.common.exception.CheckException;
import com.appgros.common.exception.CreateException;
import com.appgros.common.exception.FinderException;
import com.appgros.common.exception.RemoveException;

import java.util.List;
import java.util.Map;

/**
 * This interface gives a remote view of the OrderServiceBean. Any distant client that wants to call
 * a method on the OrderServiceBean has to use this interface.
 */
public interface IOrderService {

    // ======================================
    // =           Business methods         =
    // ======================================

    Long createOrder(final Long customerId, Map shoppingCart) throws CreateException, CheckException;
    

    OrderDTO createOrder(CartDTO orderDTO) throws CreateException, CheckException;

    OrderDTO findOrder(Long orderId) throws FinderException, CheckException;


    void deleteOrder(Long orderId) throws RemoveException, CheckException;

    List<OrderDTO>  getAllOrdersByEmail(String email);
}
