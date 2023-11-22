package news.crawler.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecItWorld implements Execute {
    @Override
    public void execute() {
        log.info("Execute itWorld");
    }
}
