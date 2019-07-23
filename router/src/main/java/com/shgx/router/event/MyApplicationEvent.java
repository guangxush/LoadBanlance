package com.shgx.router.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: guangxush
 * @create: 2019/07/23
 */
public class MyApplicationEvent extends ApplicationEvent {
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    public MyApplicationEvent(Object source) {
        super(source);
    }
}
