package com.highSchool.common.log;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.highSchool.common.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志类
 */
@Slf4j
@Component
public class LogExecutor {


    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            8,
            60,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("oper-log-pool-%d").build(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    private void submitLog() {
        SysLog sysLog = LogContext.get();
        if (sysLog == null) {
            return;
        }
        executor.submit(() -> {
            try {
                saveLog(sysLog);
            } catch (Exception e) {
                log.info("日志处理失败!", e.getMessage());
            } finally {
                LogContext.remove();
            }
        });
    }

    /**
     * 保持日志
     * @param sysLog
     */
    private void saveLog(SysLog sysLog) {
        //todo
    }

}
