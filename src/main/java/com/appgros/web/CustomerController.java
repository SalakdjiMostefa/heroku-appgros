package com.appgros.web;

import com.appgros.common.dto.CustomerDTO;
import com.appgros.common.exception.CheckException;
import com.appgros.common.exception.CreateException;
import com.appgros.server.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")

public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping("/createCustomer")
    CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) throws CheckException, CreateException, CreateException {
        return iCustomerService.createCustomer(customerDTO);
    }
}
