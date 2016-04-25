package com.lks.whozscore.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 5/27/15
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class WHOZScoreException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Holds value of property cause.
     */
    private Throwable cause;

    private ErrorCode errorCode;


    public WHOZScoreException(ErrorCode errorCode, Throwable ex){
        super(ex);
        this.errorCode = errorCode;
        this.cause = ex;

    }

    /**
     * Getter for property cause.              a
     *
     * @return Value of property cause.
     */
    public Throwable getCause() {
        return this.cause;
    }


}
