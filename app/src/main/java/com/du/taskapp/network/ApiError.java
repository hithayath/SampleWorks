package com.du.taskapp.network;

/**
 * Created by Hithayath.
 */

public class ApiError {

    private String message;
    private Throwable cause;

    public ApiError(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

}
