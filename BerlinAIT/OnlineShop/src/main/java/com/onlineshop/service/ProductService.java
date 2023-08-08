package com.onlineshop.service;

import com.onlineshop.controller.dto.ProductDTO;
import com.onlineshop.controller.dto.ProductsDTO;
import com.onlineshop.domain.Category;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.Supplier;
import com.onlineshop.repository.CategoryRepository;
import com.onlineshop.repository.ProductRepository;
import com.onlineshop.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public ProductsDTO findAll() {
        List<Product> products = productRepository.findAll();
        return ProductsDTO.getInstance(products);
    }

    public ProductDTO findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ProductDTO.getInstance(product.get());
        }
        log.error("Not found Product productId: {}", id);
        return null;
    }

    public ProductsDTO findByPartDescription(String partDescription) {
        List<Product> products = productRepository.findByDescriptionLikeIgnoreCase('%' + partDescription + '%');
        return ProductsDTO.getInstance(products);
    }

    public ProductsDTO findByCategoryId(Integer categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return ProductsDTO.getInstance(products);
    }

    public ProductDTO add(ProductDTO productDTO) {
        Product product = new Product();
        // find category
        Integer categoryId = productDTO.getCategory().getCategoryId();
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        if (!optCategory.isPresent()) {
            log.error("Not found Category categoryId: {}", categoryId);
            return null;
        }
        product.setCategory(optCategory.get());
        // find supplies
        Integer supplierId = productDTO.getSupplier().getSupplierId();
        Optional<Supplier> optSupplier = supplierRepository.findById(supplierId);
        if (!optSupplier.isPresent()) {
            log.error("Not found Supplier supplierId: {}", categoryId);
            return null;
        }
        product.setSupplier(optSupplier.get());
        // add other field
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setIsDeleted(false);
        product = productRepository.save(product);
        log.info("Product {} successfully added productId: {}", product.getProductName(), product.getProductId());
        return ProductDTO.getInstance(product);
    }

    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (!optProduct.isPresent()) {
            log.error("Not found Product productId: {}", id);
            return null;
        }
        Product product = optProduct.get();
        // update category
        Integer categoryId = productDTO.getCategory().getCategoryId();
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        if (!optCategory.isPresent()) {
            log.error("Not found Category categoryId: {}", categoryId);
            return null;
        }
        product.setCategory(optCategory.get());
        // update supplies
        Integer supplierId = productDTO.getSupplier().getSupplierId();
        Optional<Supplier> optSupplier = supplierRepository.findById(supplierId);
        if (!optSupplier.isPresent()) {
            log.error("Not found Supplier supplierId: {}", categoryId);
            return null;
        }
        product.setSupplier(optSupplier.get());
        // update other field
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setIsDeleted(productDTO.getIsDeleted());
        product = productRepository.save(product);
        return ProductDTO.getInstance(product);
    }

    public ProductDTO delete(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product delProduct = product.get();
            productRepository.delete(delProduct);
            return ProductDTO.getInstance(delProduct);
        }
        log.error("Not found Product productId: {}", id);
        return null;
    }
}
