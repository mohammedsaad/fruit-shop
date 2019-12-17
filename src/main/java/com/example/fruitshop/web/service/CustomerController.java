package com.example.fruitshop.web.service;

import com.example.fruitshop.business.domain.CustomerDTO;
import com.example.fruitshop.business.domain.CustomerListDTO;
import com.example.fruitshop.business.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId:[0-9]+}")
    ResponseEntity <CustomerDTO> getCategory(@PathVariable Long customerId){
        CustomerDTO customer =customerService.getCustomerById(customerId);
        return new ResponseEntity <CustomerDTO>(customer, HttpStatus.OK);
    }
    @GetMapping("/{customerName:[a-zA-Z]+}")
    ResponseEntity<CustomerDTO> getCategory(@PathVariable String customerName) throws NotFoundException {
        CustomerDTO customer =customerService.getCustomerByName(customerName);
        return new ResponseEntity <CustomerDTO>(customer, HttpStatus.OK);
    }
    @GetMapping("")
    ResponseEntity <CustomerListDTO> getAllCategories(){
        CustomerListDTO customers =new CustomerListDTO(customerService.getAllCustomers());
        return new ResponseEntity <CustomerListDTO>(customers, HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity <Void> addCustomer(@RequestBody CustomerDTO customerDTO, UriComponentsBuilder uriComponentsBuilder) throws NotFoundException {
        boolean flag = customerService.saveCustomer(customerDTO);
        if (!flag)
            return new ResponseEntity <Void>(HttpStatus.CONFLICT);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(customerService.getCustomerByName(customerDTO.getLastName()).getUrl()).build().toUri());

        return new ResponseEntity <Void>(headers, HttpStatus.CREATED);
    }
}
