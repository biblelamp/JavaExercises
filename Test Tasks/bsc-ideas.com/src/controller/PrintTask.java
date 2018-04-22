package controller;

/**
 * PrintTask - task for printing
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Apr 22, 2018
 */
import model.Data;

import java.util.TimerTask;

public class PrintTask extends TimerTask {
    private Data data;

    public PrintTask(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println(data);
    }
}