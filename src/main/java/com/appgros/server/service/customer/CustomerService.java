package com.appgros.server.service.customer;

import com.appgros.common.dto.CustomerDTO;
import com.appgros.common.dto.UserConnectDTO;
import com.appgros.common.dto.UserDTO;
import com.appgros.common.exception.*;
import com.appgros.server.domain.customer.Customer;
import com.appgros.server.domain.customer.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Service
public class CustomerService implements ICustomerService {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Autowired
    private CustomerDAO dao;


    // ======================================
    // =           Business methods         =
    // ======================================

    public CustomerDTO authenticate(final Long customerId, final String password) throws FinderException, CheckException {

        checkId(customerId);
        if (password == null || "".equals(password))
            throw new CheckException("Invalid password");

        // Finds the object
        final Customer customer = dao.findById(customerId).get();

        // Check if it's the right password, if not, a CheckException is thrown
        if (!password.equals(customer.getPassword()))
            throw new CheckException("Invalid password");

//        customer.matchPassword(password);

        // Transforms domain object into DTO
        final CustomerDTO customerDTO = transformCustomer2DTO(customer);

        return customerDTO;
    }

    //@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public CustomerDTO createCustomer(CustomerDTO customerDTO) throws CreateException, CheckException {

        if (customerDTO == null)
            throw new CheckException("Customer object is null");

        // Transforms DTO into domain object
        final Customer customer = new Customer();
//        customer.setPassword(customerDTO.getPassword());
//        customer.setCity(customerDTO.getCity());
//        customer.setState(customerDTO.getState());
//        customer.setStreet1(customerDTO.getAddress().getStreet1());
//        customer.setStreet2(customerDTO.getAddress().getStreet2());
//        customer.setZipcode(customerDTO.getAddress().getZipcode());
        customer.setEmail(customerDTO.getEmail());
        customer.checkData();

        /* Do not check here if the credit card is valid
        	getCreditCardService().verifyCreditCard(customer.getCreditCard());
         */
        // Creates the object
        dao.save(customer);

        // Transforms domain object into DTO
        final CustomerDTO result = transformCustomer2DTO(customer);

        return result;
    }

    //@TransactionAttribute(value = TransactionAttributeType.NEVER)
    public CustomerDTO findCustomer(final Long customerId) throws FinderException, CheckException {

        checkId(customerId);
        // Finds the object
        final Customer customer = dao.findById(customerId).get();

        // Transforms domain object into DTO
        final CustomerDTO customerDTO = transformCustomer2DTO(customer);

        return customerDTO;
    }

    //@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void deleteCustomer(final Long customerId) throws RemoveException, CheckException {

        checkId(customerId);

        // Checks if the object exists
        dao.findById(customerId);

        // Deletes the object
        dao.deleteById(customerId);
    }

    public void updateCustomer(final CustomerDTO customerDTO) throws UpdateException, CheckException {

        if (customerDTO == null)
            throw new CheckException("Customer object is null");

        checkId(customerDTO.getId());

        final Customer customer;

        // Checks if the object exists
        customer = dao.findById(customerDTO.getId()).get();

        // Transforms DTO into domain object
//        customer.getAddress().setCity(customerDTO.getAddress().getCity());
//        customer.getAddress().setState(customerDTO.getAddress().getState());
//        customer.getAddress().setStreet1(customerDTO.getAddress().getStreet1());
//        customer.getAddress().setStreet2(customerDTO.getAddress().getStreet2());
//        customer.getAddress().setZipcode(customerDTO.getAddress().getZipcode());
        customer.setEmail(customerDTO.getEmail());


        customer.checkData();
        
        /* Do not check if the credit card is valid
        	getCreditCardService().verifyCreditCard(customer.getCreditCard());
		*/
        // Updates the object
        dao.saveAndFlush(customer);
    }

    //@TransactionAttribute(value = TransactionAttributeType.NEVER)
    public Collection<CustomerDTO> findCustomers() throws FinderException {


        // Finds all the objects
        final Collection<Customer> customers = dao.findAll();

        // Transforms domain objects into DTOs
        final Collection<CustomerDTO> customersDTO = transformCustomers2DTOs(customers);

        return customersDTO;
    }

    @Override
    public void setUser(UserDTO userDTO) throws CheckException, CreateException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmail(userDTO.getEmail());
        customerDTO.setPassword(userDTO.getPassword());

//        AddressDTO addressDTO = new AddressDTO();
//        addressDTO.setStreet1(userDTO.getStreet1());
//        addressDTO.setStreet2(userDTO.getStreet2());
//        addressDTO.setCity(userDTO.getCity());
//        addressDTO.setState(userDTO.getState());
//
//        customerDTO.setAddress(addressDTO);

        createCustomer(customerDTO);

    }

    @Override
    public boolean connectUser(UserConnectDTO userDTO) {
        CustomerDTO customerDTO = findCustomerByMail(userDTO.getEmail());
        if (customerDTO == null)
            return false;

        return isExistAndCorrectPwd(userDTO, customerDTO);
    }

    private boolean isExistAndCorrectPwd(UserConnectDTO userDTO, CustomerDTO customerDTO) {
        if (userDTO.getPassword().equals(customerDTO.getPassword())) {
            return true;
        }
        return false;
    }

    private CustomerDTO findCustomerByMail(String email) {
        Customer customer = null;
        if (customer != null)
            return transformCustomer2DTO(customer);
        else
            return null;
    }


    // ======================================
    // =          Private Methods           =
    // ======================================
    private CustomerDTO transformCustomer2DTO(final Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
//        customerDTO.setPassword(customer.getPassword());
//        customerDTO.getAddress().setCity(customer.getAddress().getCity());
//        customerDTO.setEmail(customer.getEmail());
//        customerDTO.getAddress().setState(customer.getAddress().getState());
//        customerDTO.getAddress().setStreet1(customer.getAddress().getStreet1());
//        customerDTO.getAddress().setStreet2(customer.getAddress().getStreet2());
//        customerDTO.getAddress().setZipcode(customer.getAddress().getZipcode());

        return customerDTO;
    }

    private Collection<CustomerDTO> transformCustomers2DTOs(final Collection<Customer> customers) {
        final Collection<CustomerDTO> customersDTO = new ArrayList<CustomerDTO>();
        for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext(); ) {
            final Customer customer = iterator.next();
            customersDTO.add(transformCustomer2DTO(customer));
        }
        return customersDTO;
    }

    protected void checkId(final Long id) throws CheckException {
        if (id == null)
            throw new CheckException("Id should not be null or empty");
    }
}
