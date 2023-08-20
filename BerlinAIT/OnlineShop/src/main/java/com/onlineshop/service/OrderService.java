package com.onlineshop.service;

import com.onlineshop.controller.dto.OrderDTO;
import com.onlineshop.domain.*;
import com.onlineshop.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> result = new ArrayList<>();
        orders.forEach(order -> result.add(OrderDTO.getInstance(order)));
        return result;
    }

    public OrderDTO createOrder(Integer customerId, Integer shopId, Integer productId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Shop> shop = shopRepository.findById(shopId);
        // TODO check if customer and shop exists
        Order order = new Order();
        order.setCustomer(customer.get());
        order.setShop(shop.get());
        order.setOrderDate(OffsetDateTime.now());
        order.setState(OrderState.NEW);
        order = orderRepository.save(order);

        Optional<Product> product = productRepository.findById(productId);
        // TODO check if product exists
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product.get());
        orderDetail.setQuantity(1);
        orderDetail = orderDetailRepository.save(orderDetail);

        return OrderDTO.getInstance(order);
    }

    public OrderDTO addProduct(Integer orderId, Integer productId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findById(productId);
        // TODO check if order & product exists
        // TODO if state == NEW

        List<OrderDetail> orderList = orderDetailRepository.findByOrder(order.get());
        boolean productFound = false;
        for (OrderDetail orderDetail : orderList) {
            if (orderDetail.getProduct().equals(product.get())) {
                orderDetail.setQuantity(orderDetail.getQuantity() + 1);
                orderDetailRepository.save(orderDetail);
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order.get());
            orderDetail.setProduct(product.get());
            orderDetail.setQuantity(1);
            orderDetailRepository.save(orderDetail);
        }

        return OrderDTO.getInstance(order.get());
    }

    public OrderDTO deleteProduct(Integer orderId, Integer productId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findById(productId);
        // TODO check if order & product exists
        // TODO if state == NEW

        List<OrderDetail> orderList = orderDetailRepository.findByOrder(order.get());
        boolean productFound = false;
        for (OrderDetail orderDetail : orderList) {
            if (orderDetail.getProduct().equals(product.get())) {
                // TODO check if count of product after delete > 0
                orderDetail.setQuantity(orderDetail.getQuantity() - 1);
                orderDetailRepository.save(orderDetail);
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            // TODO error log+
        }

        return OrderDTO.getInstance(order.get());
    }

    public OrderDTO sendOrder(Integer orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        // TODO check if order & product exists
        // TODO if state == NEW

        Order order = optOrder.get();
        order.setState(OrderState.SENT);
        order = orderRepository.save(order);

        return OrderDTO.getInstance(order);
    }
}
