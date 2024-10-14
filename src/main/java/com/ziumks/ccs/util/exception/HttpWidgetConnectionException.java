package com.ziumks.ccs.util.exception;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpWidgetConnectionException extends Exception {
    private static final long serialVersionUID = 7237428832542072206L;

    public HttpWidgetConnectionException() {
    }

    public HttpWidgetConnectionException(String msg, Throwable cause) {
        super(msg,cause);
        log.error(msg);
    }
}