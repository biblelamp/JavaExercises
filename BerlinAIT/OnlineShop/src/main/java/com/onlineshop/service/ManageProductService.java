package com.onlineshop.service;

import com.onlineshop.controller.dto.OrderDTO;
import com.onlineshop.controller.dto.ProductInShopDTO;
import com.onlineshop.domain.*;
import com.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageProductService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInShopRepository productInShopRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public ProductInShopDTO findByShop(Integer shopId) {
        Optional<Shop> optShop = shopRepository.findById(shopId);
        // TODO check if shop exists

        List<ProductInShop> productsInShop = productInShopRepository.findByShop(optShop.get());
        ProductInShopDTO productInShopDTO = new ProductInShopDTO();
        for (ProductInShop product : productsInShop) {
            productInShopDTO.addProductItem(product.getProduct().getProductId(), product.getQuantity());
        }

        return productInShopDTO;
    }

    public OrderDTO changeOrderState(Integer orderId, OrderState orderState) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        // TODO check if order exists
        // TODO if we can to change state

        Order order = optOrder.get();
        order.setState(orderState);
        order = orderRepository.save(order);

        return OrderDTO.getInstance(order);
    }

    public ProductInShopDTO productToShop(Integer shopId, ProductInShopDTO products) {
        Optional<Shop> optShop = shopRepository.findById(shopId);
        // TODO check if shop exists

        List<ProductInShop> productsInShop = productInShopRepository.findByShop(optShop.get());

        for (ProductInShopDTO.ProductItem productItem : products.getProductItems()) {
            Optional<Product> optProduct = productRepository.findById(productItem.getProductId());
            // TODO check if product exists

            boolean foundProduct = false;
            for (ProductInShop productInShop : productsInShop) {
                if (productInShop.getProduct().equals(optProduct.get())) {
                    productInShop.setQuantity(productInShop.getQuantity() + productItem.getQuantity());
                    productInShopRepository.save(productInShop);
                    foundProduct = true;
                    break;
                }
            }
            if (!foundProduct) {
                ProductInShop productInShop = new ProductInShop();
                productInShop.setShop(optShop.get());
                productInShop.setProduct(optProduct.get());
                productInShop.setQuantity(productItem.getQuantity());
                productInShop = productInShopRepository.save(productInShop);
                productsInShop.add(productInShop);
            }
        }
        productInShopRepository.saveAll(productsInShop);

        return products;
    }

    public OrderDTO getProductsByOrder(Integer orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        // TODO check if order exists
        // TODO if order has property state

        Order order = optOrder.get();
        List<ProductInShop> productsInShop = productInShopRepository.findByShop(order.getShop());
        List<OrderDetail> productsInOrder = orderDetailRepository.findByOrder(order);

        // TODO optimization: create mapProductInShop<productId, productInShop>

        for (OrderDetail orderDetail : productsInOrder) {
            boolean foundProduct = false;
            for (ProductInShop productInShop : productsInShop) {
                if (orderDetail.getProduct().equals(productInShop.getProduct())) {
                    productInShop.setQuantity(productInShop.getQuantity() - orderDetail.getQuantity());
                    // TODO if quantity not negative after operation
                    // if error then log and return null
                    productInShopRepository.save(productInShop);
                    foundProduct = true;
                    break;
                }
            }
            if (!foundProduct) {
                // TODO log error
                return null;
            }
        }
        order.setState(OrderState.DELIVERED);
        order = orderRepository.save(order);

        return OrderDTO.getInstance(order);
    }
}
