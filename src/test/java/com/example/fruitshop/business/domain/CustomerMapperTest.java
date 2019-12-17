package com.example.fruitshop.business.domain;

import com.example.fruitshop.model.entity.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    public static final String FIRST_NAME = "Ali";
    public static final String LAST_NAME = "Nasser";
    private CustomerMapper customerMapper;


    @Test
    void customerTOCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        //when
        CustomerDTO customerDTO =    customerMapper.INSTANCE.customerTOCustomerDTO(customer);
        //then
        assertEquals(customerDTO.getFirstName(),FIRST_NAME);
        assertEquals(customerDTO.getLastName(),LAST_NAME);

    }
}