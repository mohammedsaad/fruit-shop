package com.example.fruitshop.bootstrap;

import com.example.fruitshop.model.entity.Category;
import com.example.fruitshop.model.entity.Customer;
import com.example.fruitshop.model.repository.CategoryRepository;
import com.example.fruitshop.model.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        Customer mohammed =new Customer();
        mohammed.setFirstName("Mohammed");
        mohammed.setLastName("Abdelal");
        Customer fadi = new Customer();
        fadi.setLastName("Almobayed");
        fadi.setFirstName("Fadi");
        Customer ahmed = new Customer();
        ahmed.setFirstName("Ahmed");
        ahmed.setLastName("AbuMusameh");

        customerRepository.save(mohammed);
        customerRepository.save(fadi);
        customerRepository.save(ahmed);

        System.out.println("Data Loaded = " + categoryRepository.count() );
    }
}
