package com.onlineshop.service;

import com.onlineshop.controller.dto.SupplierDTO;
import com.onlineshop.domain.Country;
import com.onlineshop.domain.Supplier;
import com.onlineshop.repository.CountryRepository;
import com.onlineshop.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<SupplierDTO> findAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDTO> result = new ArrayList<>();
        suppliers.forEach(supplier -> result.add(SupplierDTO.getInstance(supplier)));
        return result;
    }

    public SupplierDTO add(SupplierDTO supplier) {
        Supplier newSupplier = new Supplier();
        newSupplier.setSupplierName(supplier.getSupplierName());
        newSupplier.setAddress(supplier.getAddress());
        Integer countryId = supplier.getCountry().getCountryId();
        Optional<Country> optCountry = countryRepository.findById(countryId);
        if (!optCountry.isPresent()) {
            log.error("Not found Country countryId: {}", countryId);
            return null;
        }
        newSupplier.setCountry(optCountry.get());
        newSupplier = supplierRepository.save(newSupplier);
        log.info("Supplier {} successfully added.", supplier.getSupplierName());
        return SupplierDTO.getInstance(newSupplier);
    }

    public SupplierDTO update(Integer id, SupplierDTO supplierDTO) {
        Optional<Supplier> optSupplier = supplierRepository.findById(id);
        if (optSupplier.isPresent()) {
            Supplier updSupplier = optSupplier.get();
            updSupplier.setSupplierName(supplierDTO.getSupplierName());
            updSupplier.setAddress(supplierDTO.getAddress());
            Integer countryId = supplierDTO.getCountry().getCountryId();
            Optional<Country> optCountry = countryRepository.findById(countryId);
            if (!optCountry.isPresent()) {
                log.error("Not found Country countryId: {}", countryId);
                return null;
            }
            updSupplier.setCountry(optCountry.get());
            updSupplier = supplierRepository.save(updSupplier);
            return SupplierDTO.getInstance(updSupplier);
        }
        log.error("Not found Supplier {} supplierId: {}", supplierDTO.getSupplierName(), id);
        return null;
    }

    public SupplierDTO delete(Integer id) {
        Optional<Supplier> optSupplier = supplierRepository.findById(id);
        if (optSupplier.isPresent()) {
            Supplier delSupplier = optSupplier.get();
            supplierRepository.delete(delSupplier);
            return SupplierDTO.getInstance(delSupplier);
        }
        log.error("Not found Supplier supplierId: {}", id);
        return null;
    }
}
