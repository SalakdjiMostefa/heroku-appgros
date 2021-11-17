package com.appgros.server.service.customer;

import com.appgros.common.dto.CustomerDTO;
import com.appgros.common.dto.UserConnectDTO;
import com.appgros.common.dto.UserDTO;
import com.appgros.common.exception.*;

import java.util.Collection;


public interface ICustomerService {

    // ======================================
    // =           Business methods         =
    // ======================================

    CustomerDTO authenticate(Long customerId, String password) throws FinderException, CheckException;

    CustomerDTO createCustomer(CustomerDTO customerDTO) throws CreateException, CheckException;

    CustomerDTO findCustomer(Long customerId) throws FinderException, CheckException;

    void deleteCustomer(Long customerId) throws RemoveException, CheckException;

    void updateCustomer(CustomerDTO customerDTO) throws UpdateException, CheckException;

    Collection<CustomerDTO> findCustomers() throws FinderException;

    void setUser(UserDTO userDTO) throws CheckException, CreateException;

    boolean connectUser(UserConnectDTO userDTO);
}
