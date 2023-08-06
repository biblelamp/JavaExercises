package com.onlineshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Операции с заказами (выполняет покупатель):
 * - создать заказ с товаром
 * - добавить товар в заказ
 * - удалить товар из заказа
 * - отправить заказ на исполнение
 */
@RestController
@RequestMapping("/order")
public class OrderController {
}
