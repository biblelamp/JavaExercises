package de.pizza.tomate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Пользователь: администратор
 * 1. Управляет справочником PizzaBase
 *    - создает, изменяет, удаляет, восстанавливает
 * 2. Управляет справочником PizzaType
 * 3. Управляет справочником PizzaSize
 * 4. Управляет справочником Ingredient
 * 5. Управляет профилями заказчиков и своим User (-)
 * 6. Управляет заказами
 *    - изменяет статус заказа: отправка, отмена и т.д.
 *    - читает список всех заказов
 *
 * Пользователь: заказчик
 * 1. Управление своим профилем User (-)
 * 2. Управляет заказами
 *    - создает заказ
 *    - удаляет свой заказ
 *    - добавляет и удаляет пиццы Pizza из Order
 *    - добавляет и удаляет ингредиенты Ingredient у Pizza
 *    - подтверждает заказ CONFIRMED
 *    - читает заказ
 *    - читает список своих заказов
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
