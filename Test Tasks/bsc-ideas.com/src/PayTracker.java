/**
 * PayTracker - Payment Tracker
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Apr 22, 2018
 */
import controller.Controller;
import model.Data;

public class PayTracker {

    public static void main(String[] args) {
        new Controller(new Data(), args);
    }
}