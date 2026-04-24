package com.highSchool.common.log;

import com.highSchool.common.entity.SysLog;
import lombok.Data;

@Data
public class LogContext {

    public static ThreadLocal<SysLog> LOG_LOCAL = new ThreadLocal<>();

    public static void set(SysLog sysLog) {
        LOG_LOCAL.set(sysLog);
    }

    public static SysLog get() {
        return LOG_LOCAL.get();
    }

    public static void remove() {
        LOG_LOCAL.remove();
    }
}
