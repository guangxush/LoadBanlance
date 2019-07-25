package com.shgx.server.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @author: guangxush
 * @create: 2019/07/25
 */
public class LoggerCache {
    /**
     * 日志实例记录在内存中
     */
    private static HashMap<String, Logger> LOGERS = new HashMap<>();

    /**
     * 根据类名获取缓存的日志实例
     * @param className 包名加类名 this.getClass().getName();
     * @return
     */
    public static Logger getLoggerByClassName(String className) {
        // 从静态map中获取日志实例
        Logger logger = LOGERS.get(className);
        // 如果没取到
        if (logger == null) {
            // 创建一个日志实例
            logger = LoggerFactory.getLogger(className);
            // 加入到静态map中
            LOGERS.put(className, logger);
        }
        // 返回
        return logger;
    }
}
