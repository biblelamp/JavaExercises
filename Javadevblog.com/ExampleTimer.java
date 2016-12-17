import java.util.*;

public class ExampleTimer extends TimerTask {

    @Override
    public void run() {
        System.out.println("TimerTask started at: " + new Date());
        completeTask();
        System.out.println("TimerTask finished at: " + new Date());
    }

    private void completeTask() {
        try {
            Thread.sleep(20000); // 20 secund delay
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]){
        TimerTask timerTask = new ExampleTimer();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10*1000); // every 10 seconds (10 * 1000 milliseconds)
        System.out.println("TimerTask started to execution");

        try {
            Thread.sleep(120000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        timer.cancel();
        System.out.println("TimerTask is stopped.");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}