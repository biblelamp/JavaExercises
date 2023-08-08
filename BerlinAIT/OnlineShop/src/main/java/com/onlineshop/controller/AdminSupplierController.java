package com.onlineshop.controller;

import com.onlineshop.controller.dto.SupplierDTO;
import com.onlineshop.service.SupplierService;
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
@RequestMapping("/admin/supplier")
public class AdminSupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/all")
    private List<SupplierDTO> findAll() {
        return supplierService.findAll();
    }

    @PostMapping("/add")
    public SupplierDTO addSupplier(@RequestBody SupplierDTO supplier) {
        return supplierService.add(supplier);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@RequestBody SupplierDTO supplier, @PathVariable Integer id) {
        SupplierDTO updSupplierDTO = supplierService.update(id, supplier);
        if (updSupplierDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updSupplierDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SupplierDTO> deleteCustomer(@PathVariable Integer id) {
        SupplierDTO delSupplierDTO = supplierService.delete(id);
        if (delSupplierDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(delSupplierDTO);
    }
}
