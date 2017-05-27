package com.ws.stoner.exception;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public class AuthExpireException extends ServiceException{
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AuthExpireException(String message) {
        super(message);
    }
}
