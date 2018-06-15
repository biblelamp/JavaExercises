/**
 * Class MainApp
 * The main class which makes 2 reports according test task
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Oct 11, 2017
 */
class MainApp {

    final static String MENU_FILE = "menu.xml";
    final static String ORDERS_FILE = "orders.xml";

    public static void main(String[] args) {
        Menu menu = new Menu(MENU_FILE);
        Orders orders = new Orders(ORDERS_FILE);
        Report report = new Report(menu, orders);
        report.print4Cook();
        report.print4Client();
    }
}