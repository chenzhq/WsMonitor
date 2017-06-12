package com.ws.stoner.exception;

/**
 * Created by chenzheqi on 2017/5/22.
 */
public class ManagerException extends Exception{
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ManagerException(String message) {
        super(message);
    }

}
