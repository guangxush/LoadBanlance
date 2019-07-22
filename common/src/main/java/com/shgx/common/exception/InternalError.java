package com.shgx.common.exception;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
public class InternalError extends RuntimeException{
    public InternalError(String message, String s) {

    }

    public InternalError(String message) {
        super(message);
    }

    public InternalError(String message, Throwable e){
        super(message, e);
    }
}
