package com.example.fruitshop.business.service;

import com.example.fruitshop.business.domain.CustomerDTO;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {
    public List <CustomerDTO> getAllCustomers();
    public CustomerDTO getCustomerByName(String name) throws NotFoundException;
    public CustomerDTO getCustomerById(Long id);
    public boolean saveCustomer(CustomerDTO customerDTO);
}
