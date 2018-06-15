/**
 * Class Report
 * The class class processes data and prints reports
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 11, 2017
 */
import java.util.*;

class Report {
    private Menu menu;
    private Orders orders;

    Report(Menu menu, Orders orders) {
        this.menu = menu;
        this.orders = orders;
    }

    public void print4Cook() { // report for kitchen
        Map<String, Integer> dishes = orders.getDishes(menu);
        int total = 0;
        System.out.printf("%-30s%10s%10s\n",
            "NAMES OF DISHES", "QUANTITY", "SUM");
        for (Map.Entry<String, Integer> item : dishes.entrySet()) {
            String id = item.getKey();
            int quantity = item.getValue();
            int sum = quantity * menu.getPrice(id);
            total += sum;
            System.out.printf("%-30.30s%10d%10d\n",
                menu.getName(id), quantity, sum);
        }
        System.out.printf("%-30s%20d\n\n", "TOTAL:", total);
    }

    public void print4Client() { // report for customers
        int total;
        Map<String, String[]> ords = orders.getOrders();
        System.out.printf("%-15s%-30s%10s\n",
            "CUSTOMER", "DISHES", "PRICE");
        for (Map.Entry<String, String[]> item : ords.entrySet()) {
            total = 0;
            System.out.printf("%s\n", item.getKey());
            for (String id : item.getValue()) {
                System.out.printf("%-15s%-30.30s%10d\n",
                    "", menu.getName(id), menu.getPrice(id));
                total += menu.getPrice(id);
            }
            System.out.printf("%-15s%-30s%10d\n", "", "TOTAL:", total);
        }
    }
}