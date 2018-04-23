/**
 * PayTracker - Payment Tracker
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Apr 23, 2018
 */
import controller.Controller;
import model.Data;
import model.Rate;

public class PayTracker {

    public static void main(String[] args) {
        new Controller(new Data(new Rate()), args);
    }
}