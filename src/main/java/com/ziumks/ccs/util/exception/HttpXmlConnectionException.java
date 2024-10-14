package com.ziumks.ccs.util.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpXmlConnectionException extends Exception {
    private static final long serialVersionUID = 7237428832542072206L;

    public HttpXmlConnectionException() {
    }

    public HttpXmlConnectionException(String msg,Throwable cause) {
        super(msg,cause);
        log.error(msg);
    }
}