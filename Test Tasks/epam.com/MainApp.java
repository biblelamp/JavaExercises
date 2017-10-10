/**
 * Class MainApp
 * The main class which makes 2 reports according test task
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 10, 2017
 */
class MainApp {

    final static String MENU_FILE = "menu.xml";
    final static String ORDERS_FILE = "orders.xml";

    public static void main(String[] args) {
        Menu menu = new Menu(MENU_FILE);
        System.out.println(menu);
        Orders orders = new Orders(ORDERS_FILE);
        System.out.println(orders);
    }
}