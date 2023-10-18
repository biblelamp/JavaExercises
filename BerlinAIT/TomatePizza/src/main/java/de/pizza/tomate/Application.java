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
 * 5. Управляет заказами
 *    - изменяет статус заказа: отправка, отмена
 *    - видит историю заказов
 *
 * Пользователь: заказчик
 * 1. Управление своим профилем
 * 2. Управляет заказами
 *    - создает заказ
 *    - читает заказ
 *    - добавляет и удаляет пиццы Pizza из заказа
 *    - добавляет и удаляет ингредиенты Ingredient у Pizza
 *    - отправляет заказ
 *    - видит историю своих заказов
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
