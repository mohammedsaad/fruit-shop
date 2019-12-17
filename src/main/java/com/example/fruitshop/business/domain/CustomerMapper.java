package com.example.fruitshop.business.domain;

import com.example.fruitshop.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO customerTOCustomerDTO(Customer customer);
    Customer customerDTOTOCustomer(CustomerDTO customerDTO);
}
