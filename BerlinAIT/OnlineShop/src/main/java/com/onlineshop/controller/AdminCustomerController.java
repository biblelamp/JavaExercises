package com.onlineshop.controller;

import com.onlineshop.controller.dto.CustomerDTO;
import com.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/customer")
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    private List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/add")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customer) {
        return customerService.add(customer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customer, @PathVariable Integer id) {
        CustomerDTO updCustomerDTO = customerService.update(id, customer);
        if (updCustomerDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updCustomerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Integer id) {
        CustomerDTO delCustomerDTO = customerService.delete(id);
        if (delCustomerDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(delCustomerDTO);
    }
}
