package com.example.fruitshop.business.service;

import com.example.fruitshop.business.domain.CustomerDTO;
import com.example.fruitshop.business.domain.CustomerMapper;
import com.example.fruitshop.model.entity.Customer;
import com.example.fruitshop.model.repository.CustomerRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class,SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerServiceImplTest {

    public static final String SAAD = "saad";
    public static final long ID = 1L;
    public static final String ABDELAL = "Abdelal";
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerServiceImpl= new CustomerServiceImpl(customerRepository,CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        //given
        Customer saad =new Customer();
        saad.setFirstName(SAAD);
        saad.setLastName("Abdelal");
        Customer ez = new Customer();
        ez.setLastName("Ez");
        ez.setFirstName("Nadir");
        List<Customer> customers = Arrays.asList(saad,ez);
        when(customerRepository.findAll()).thenReturn(customers);
        //when
        List<CustomerDTO> customerDTOS = customerServiceImpl.getAllCustomers();
        //then
        assertEquals(customerDTOS.size(),2);
        assertEquals(customerDTOS.get(0).getFirstName(),SAAD);
    }

    @Test
    void getCustomerByName() throws NotFoundException {
        //given
        Customer saad =new Customer();
        saad.setFirstName(SAAD);
        saad.setLastName(ABDELAL);
        when(customerRepository.findByLastName(any())).thenReturn(Optional.of(saad));
        //when
        CustomerDTO customerDTO=customerServiceImpl.getCustomerByName("saad");
        //then
        assertEquals(customerDTO.getFirstName(),SAAD);
    }

    @Test
    void getCustomerById() {
        //given
        Customer saad =new Customer();
        saad.setId(ID);
        saad.setFirstName(SAAD);
        saad.setLastName(ABDELAL);
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(saad));
        //when
        CustomerDTO customerDTO=customerServiceImpl.getCustomerById(1L);
        //then
        assertEquals(customerDTO.getLastName(),ABDELAL);
        assertEquals(customerDTO.getFirstName(),SAAD);
    }

    @Test
    void saveCustomer() {
        Customer saad =new Customer();
        saad.setId(ID);
        saad.setFirstName(SAAD);
        saad.setLastName(ABDELAL);
        when(customerRepository.exists(any())).thenReturn(true);
        CustomerMapper customerMapper = CustomerMapper.INSTANCE;
        //when

        //then
        assertFalse(customerServiceImpl.saveCustomer(customerMapper.customerTOCustomerDTO(saad)));

    }
}