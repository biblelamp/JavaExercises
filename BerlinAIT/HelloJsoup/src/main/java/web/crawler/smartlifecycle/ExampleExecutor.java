package web.crawler.smartlifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

@Service
public class ExampleExecutor implements SmartLifecycle {

    static final Logger log = LoggerFactory.getLogger(SmartLifecycle.class);

    private enum ThreadStatus {
        RUNNING, STOP_REQUEST, STOPPED
    }

    private ThreadStatus status = ThreadStatus.STOPPED;
    private final Object lock = new Object();

    public void run() {
        synchronized (lock) {
            while (status == ThreadStatus.RUNNING) {
                log.info("Service exec...");
                try {
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    break;
                }
            }
            status = ThreadStatus.STOPPED;
            lock.notifyAll();
        }
    }

    @Override
    public void start() {
        log.info("Starting service...");
        status = ThreadStatus.RUNNING;
        new Thread(() -> run()).start();
        log.info("Service is started.");
    }

    @Override
    public void stop() {
        log.info("Stopping service...");
        status = ThreadStatus.STOP_REQUEST;

        synchronized (lock) {
            lock.notifyAll();
            while (status != ThreadStatus.STOPPED) {
                try {
                    lock.wait(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    break;
                }
            }
        }
        log.info("Service is stopped.");
    }

    @Override
    public boolean isRunning() {
        return status == ThreadStatus.RUNNING;
    }
}
