package com.onlineshop.service;

import com.onlineshop.controller.dto.CustomerDTO;
import com.onlineshop.domain.Country;
import com.onlineshop.domain.Customer;
import com.onlineshop.repository.CountryRepository;
import com.onlineshop.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> result = new ArrayList<>();
        customers.forEach(customer -> result.add(CustomerDTO.getInstance(customer)));
        return result;
    }

    public CustomerDTO add(CustomerDTO customerDTO) {
        Customer newCustomer = newOrUpdateCustomer(new Customer(), customerDTO);
        if (newCustomer == null) {
            return null;
        }
        log.info("Customer {} successfully added.", customerDTO.getCustomerName());
        return CustomerDTO.getInstance(newCustomer);
    }

    public CustomerDTO update(Integer id, CustomerDTO customerDTO) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isPresent()) {
            Customer updCustomer = newOrUpdateCustomer(optCustomer.get(), customerDTO);
            if (updCustomer == null) {
                return null;
            }
            return CustomerDTO.getInstance(updCustomer);
        }
        log.error("Not found Customer {} customerId: {}", customerDTO.getCustomerName(), id);
        return null;
    }

    private Customer newOrUpdateCustomer(Customer customer, CustomerDTO customerDTO) {
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setAddress(customerDTO.getAddress());
        Integer countryId = customerDTO.getCountry().getCountryId();
        Optional<Country> optCountry = countryRepository.findById(countryId);
        if (!optCountry.isPresent()) {
            log.error("Not found Country countryId: {}", countryId);
            return null;
        }
        customer.setCountry(optCountry.get());
        return customerRepository.save(customer);
    }

    public CustomerDTO delete(Integer id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isPresent()) {
            Customer delCustomer = optCustomer.get();
            customerRepository.delete(delCustomer);
            return CustomerDTO.getInstance(delCustomer);
        }
        log.error("Not found Customer customerId: {}", id);
        return null;
    }
}
