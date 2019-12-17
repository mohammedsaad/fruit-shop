package com.example.fruitshop.business.service;

import com.example.fruitshop.business.domain.CustomerDTO;
import com.example.fruitshop.business.domain.CustomerMapper;
import com.example.fruitshop.exception.ResourceNotFoundException;
import com.example.fruitshop.model.entity.Customer;
import com.example.fruitshop.model.repository.CustomerRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    public static final String API_CUSTOMERS_URL = "/api/customers/";
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List <CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerTOCustomerDTO(customer);
                    customerDTO.setUrl(API_CUSTOMERS_URL +customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByName(String name) throws NotFoundException {

        return customerRepository.findByLastName(name)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerTOCustomerDTO(customer);
                    customerDTO.setUrl(API_CUSTOMERS_URL+customer.getId());
                    return customerDTO;
                })
                .orElseThrow(() -> new ResourceNotFoundException(" No Resource with name : " + name));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerTOCustomerDTO(customer);
                    customerDTO.setUrl(API_CUSTOMERS_URL+customer.getId());
                    return customerDTO;
                })
                .orElseThrow(() -> new ResourceNotFoundException("No Resource with id : " + id));
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOTOCustomer(customerDTO);
        boolean flag =customerRepository.exists(Example.of(customer));
        if (flag)
            return false;
        else{
        customerRepository.save(customer);
        return true ;
    }
    }

}
