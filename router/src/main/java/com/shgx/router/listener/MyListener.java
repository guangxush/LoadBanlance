package com.shgx.router.listener;

import com.shgx.router.event.MyApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * @author: guangxush
 * @create: 2019/07/23
 */
@Slf4j
public class MyListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        String msg = event.getMsg();
        log.info(msg);
    }
}
