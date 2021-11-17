package com.appgros.server.service.order;

import com.appgros.server.domain.order.OrderDAO;
import com.appgros.common.dto.CartDTO;
import com.appgros.common.dto.OrderDTO;
import com.appgros.common.dto.OrderLineDTO;
import com.appgros.common.exception.CheckException;
import com.appgros.common.exception.CreateException;
import com.appgros.common.exception.FinderException;
import com.appgros.common.exception.RemoveException;
import com.appgros.server.domain.customer.Customer;
import com.appgros.server.domain.customer.CustomerDAO;
import com.appgros.server.domain.item.Item;
import com.appgros.server.domain.item.ItemDAO;
import com.appgros.server.domain.order.Order;
import com.appgros.server.domain.orderline.OrderLine;
import com.appgros.server.domain.orderline.OrderLineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderLineDAO orderLineDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ItemDAO itemDAO;


    private String CREATE_ORDER = "createOrder";

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderService() {
    }


    // ======================================
    // =           Business methods         =
    // ======================================
    public Long createOrder(final Long customerId, Map shoppingCart) throws CreateException, CheckException {


        // Finds the customer
        Customer customer = null;
        customer = customerDAO.findById(customerId).get();

        // Creates a new order
        final Order order = new Order();
//                customer.getStreet1(), customer.getCity(),
//                customer.getZipcode(), customer);
//        order.setStreet2(customer.getAddress().getStreet2());
//        order.getAddress().setState(customer.getAddress().getState());

        // Creates the order
        orderDAO.save(order);

        // Creates all the orderLines linked with the order
        Iterator iterator = shoppingCart.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry keyValue = (Map.Entry) iterator.next();
            Long itemId = (Long) keyValue.getKey();
            int quantity = (Integer) keyValue.getValue();
            // Finds the item
            Item item = null;
            item = itemDAO.findById(itemId).get();
            // Creates OrderLine
            final OrderLine orderLine = new OrderLine();
//            final OrderLine orderLine = new OrderLine(quantity, item.getUnitCost(), order, item);

            // Creates the order line
            orderLineDAO.save(orderLine);
        }

        return order.getId();
    }

    public OrderDTO createOrder(final CartDTO cartDTO) throws CreateException, CheckException {

        if (cartDTO == null)
            throw new CreateException("Cart object is null");

        if (cartDTO.getCartLineDTOS() == null || cartDTO.getCartLineDTOS().size() <= 0)
            throw new CheckException("There are no order lines");

        // Finds the customer
        Customer customer = null;
        customer = customerDAO.findById(cartDTO.getCustomerId()).get();

        // Transforms Order DTO into domain object
        final Order order = new Order();
//                cartDTO.getAddress().getStreet1(), cartDTO.getAddress().getCity(),
//                cartDTO.getAddress().getZipcode(), customer);
//        order.getAddress().setStreet2(cartDTO.getAddress().getStreet2());
//        order.getAddress().setState(cartDTO.getAddress().getState());

        // Creates the order
        orderDAO.save(order);

        // Creates all the orderLines linked with the order
        Collection orderLines = new ArrayList();
        for (Iterator iterator = cartDTO.getCartLineDTOS().iterator(); iterator.hasNext(); ) {
            final OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();
            // Finds the item
            Item item = null;
            item = itemDAO.findById(orderLineDTO.getItemId()).get();
            // Transforms OrderLine DTO into domain object
//            final OrderLine orderLine = new OrderLine(orderLineDTO.getQuantity(), orderLineDTO.getUnitCost(), order, item);
            final OrderLine orderLine = new OrderLine();
            // Creates the order line
            orderLineDAO.save(orderLine);
            orderLines.add(orderLine);
        }
        // Sets orderLines into the order
        order.setOrderLines(orderLines);
        orderDAO.saveAndFlush(order);

        // Transforms domain object into DTO
        final OrderDTO result = transformOrder2DTO(order);


        return result;
    }


    public OrderDTO findOrder(final Long orderId) throws FinderException, CheckException {

        if (orderId == null || "".equals(orderId))
            throw new CheckException("Order object can't be null or empty");

        // Finds the object
        Order order = orderDAO.getOne(orderId);

        // Retreives the data for the customer and sets it
        Customer customer = (Customer) customerDAO.getOne(order.getCustomer().getId());
        order.setCustomer(customer);

        // Retreives the data for all the order lines
//        final Collection orderLines = orderLineDAO.findAllInOrder(orderId);
//        order.setOrderLines(orderLines);

        // Transforms domain object into DTO
        final OrderDTO orderDTO = transformOrder2DTO(order);

        return orderDTO;
    }

    public void deleteOrder(final Long orderId) throws RemoveException, CheckException {

        if (orderId == null || "".equals(orderId))
            throw new CheckException("Order object can't be null or empty");

        // Checks if the object exists
        orderDAO.getOne(orderId);

        // Deletes the object
        orderDAO.deleteById(orderId);
    }

    @Override
    public List<OrderDTO> getAllOrdersByEmail(String email) {
        Customer customer = customerDAO.getById(1L);
//        List<Order> orders = (List<Order>) orderDAO.getAllByCustomer_Id(customer.getId());
        List<OrderDTO> result = new ArrayList<>();
//        for (Order order : orders) {
//            OrderDTO orderDTO = new OrderDTO();
//            orderDTO = transformOrder2DTO(order);
//            result.add(orderDTO);
//        }

        return result;
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private OrderDTO transformOrder2DTO(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setOrderDate(order.getOrderDate());
        // Transforms all the order lines
        orderDTO.setOrderLines(transformOrderLines2DTOs(order.getOrderLines()));
        return orderDTO;
    }

    private Collection transformOrderLines2DTOs(final Collection orderLines) {
        final Collection orderLinesDTO = new ArrayList();
        OrderLineDTO orderLineDTO;
        for (Iterator iterator = orderLines.iterator(); iterator.hasNext(); ) {
            final OrderLine orderLine = (OrderLine) iterator.next();
            orderLineDTO = new OrderLineDTO();
            orderLineDTO.setItemId(orderLine.getItem().getId());
            orderLineDTO.setItemName(orderLine.getItem().getName());
            orderLineDTO.setQuantity(orderLine.getQuantity());
            orderLineDTO.setUnitCost(orderLine.getUnitCost());
            orderLinesDTO.add(orderLineDTO);
        }
        return orderLinesDTO;
    }

}
