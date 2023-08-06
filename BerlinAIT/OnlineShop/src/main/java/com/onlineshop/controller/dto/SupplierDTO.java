package com.onlineshop.controller.dto;

import com.onlineshop.domain.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SupplierDTO {
    private final Integer supplierId;
    private final String supplierName;
    private final String address;
    private final CountryDTO country;

    public static SupplierDTO getInstance(Supplier supplier) {
        return new SupplierDTO(supplier.getSupplierId(), supplier.getSupplierName(), supplier.getAddress(),
                CountryDTO.getInstance(supplier.getCountry()));
    }
}
