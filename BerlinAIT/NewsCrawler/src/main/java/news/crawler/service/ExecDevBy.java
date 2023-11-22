package news.crawler.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecDevBy implements Execute {
    @Override
    public void execute() {
        log.info("Execute devBy");
    }
}
