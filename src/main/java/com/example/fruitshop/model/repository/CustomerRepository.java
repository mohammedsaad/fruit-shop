package com.example.fruitshop.model.repository;

import com.example.fruitshop.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional <Customer> findByLastName(String name);

}
