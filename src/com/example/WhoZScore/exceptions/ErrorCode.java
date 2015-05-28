package com.example.WhoZScore.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 5/27/15
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ErrorCode {
    ERROR_CONNECTING_TO_DATABASE("Error while connecting to the database");

    private String message;

    private ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
