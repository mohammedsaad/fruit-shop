package com.example.fruitshop.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerListDTO {

    List <CustomerDTO> customers;
}