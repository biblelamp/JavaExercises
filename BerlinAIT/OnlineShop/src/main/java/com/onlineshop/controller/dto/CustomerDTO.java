package com.onlineshop.controller.dto;

import com.onlineshop.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerDTO {
    private final Integer customerId;
    private final String customerName;
    private final String address;
    private final CountryDTO country;

    public static CustomerDTO getInstance(Customer customer){
        return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getAddress(),
                CountryDTO.getInstance(customer.getCountry()));
    }
}
