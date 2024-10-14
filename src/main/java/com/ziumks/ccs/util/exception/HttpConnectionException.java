package com.ziumks.ccs.util.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpConnectionException extends Exception {
    private static final long serialVersionUID = 7237428832542072206L;

    public HttpConnectionException() {
    }
    public HttpConnectionException(String msg) {
        super(msg);
        log.error(msg);
    }
}