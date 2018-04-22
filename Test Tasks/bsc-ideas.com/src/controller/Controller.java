package controller;

/**
 * Controller - processing commands and starting a timer
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Apr 22, 2018
 */
import model.Data;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    final private Scanner scanner = new Scanner(System.in);
    final private int minute = 1000 * 60; // 1 minute
    final private String CMD_QUIT = "quit";

    public Controller(Data data, String[] args) {
        if (args.length > 0)
            data.addFromFile(args[0]);
        TimerTask timerTask = new PrintTask(data);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, minute);
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase(CMD_QUIT))
                break;
            data.processLine(line);
        }
    }
}