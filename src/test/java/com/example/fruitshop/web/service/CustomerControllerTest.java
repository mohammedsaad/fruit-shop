package com.example.fruitshop.web.service;

import com.example.fruitshop.business.domain.CustomerDTO;
import com.example.fruitshop.business.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @MockBean
    CustomerService customerService;
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCustomers() throws Exception {
        //given
        CustomerDTO saad =new CustomerDTO();
        saad.setFirstName("Saad");
        saad.setLastName("Abdelal");
        CustomerDTO ez = new CustomerDTO();
        ez.setLastName("Ez");
        ez.setFirstName("Nadir");
        List <CustomerDTO> customers = Arrays.asList(saad,ez);
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.customers",hasSize(2)));
    }

    @Test
    void testGetCustomerById() throws Exception {
        CustomerDTO saad =new CustomerDTO();
        saad.setFirstName("Saad");
        saad.setLastName("Abdelal");

        when(customerService.getCustomerById(any(Long.class))).thenReturn(saad);

        mockMvc.perform(get("/api/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo("Saad")));
    }


    @Test
    void testGetCustomerByName() throws Exception {
        CustomerDTO saad =new CustomerDTO();
        saad.setFirstName("Saad");
        saad.setLastName("Abdelal");

        when(customerService.getCustomerByName(any(String.class))).thenReturn(saad);

        mockMvc.perform(get("/api/customers/saad").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo("Saad")));
    }
}