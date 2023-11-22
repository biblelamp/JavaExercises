package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.domain.SourceConfig;
import news.crawler.repository.SourceConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
@Service
public class CrawlerExecutor implements SmartLifecycle {

    //static final Logger log = LoggerFactory.getLogger(CrawlerExecutor.class);

    @Autowired
    private SourceConfigRepository sourceConfigRepository;

    private enum ThreadStatus {
        RUNNING, STOP_REQUEST, STOPPED
    }

    private ThreadStatus status = ThreadStatus.STOPPED;
    private final Object lock = new Object();

    public void run() {
        synchronized (lock) {
            while (status == ThreadStatus.RUNNING) {

                List<SourceConfig> configs = sourceConfigRepository.findAll();
                for (SourceConfig config : configs) {
                    try {
                        Class<?> cls = Class.forName(config.getClassName());
                        Constructor<?> constructor = cls.getConstructor();
                        Execute execClass = (Execute) constructor.newInstance();
                        execClass.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                try {
                    lock.wait(1000 * 5);
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
        log.info("Service starting...");
        status = ThreadStatus.RUNNING;
        new Thread(() -> {
            run();
        }).start();
        log.info("Service is started.");
    }

    @Override
    public void stop() {
        log.info("Service stopping...");
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
