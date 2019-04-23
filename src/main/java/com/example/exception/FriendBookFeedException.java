// (C) Copyright 2019 Hewlett Packard Enterprise Development LP

package com.example.exception;

/**
 *
 */
public class FriendBookFeedException extends Throwable
{
    private static final long serialVersionUID = 1L;
    int errorCode;
    String message;

    /**
     * @param errorCode
     * @param message
     */
    public FriendBookFeedException(final int errorCode, final String message)
    {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * Default constructor
     */
    public FriendBookFeedException()
    {
        super();

    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public FriendBookFeedException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message=message;
    }

    /**
     * @param message
     * @param cause
     */
    public FriendBookFeedException(final String message, final Throwable cause)
    {
        super(message, cause);
        this.message=message;
    }

    /**
     * @param message
     */
    public FriendBookFeedException(final String message)
    {
        super(message);
        this.message=message;
    }

    /**
     * @param cause
     */
    public FriendBookFeedException(final Throwable cause)
    {
        super(cause);

    }

    /**
     * @return the errorCode
     */
    public int getErrorCode()
    {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(final int errorCode)
    {
        this.errorCode = errorCode;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message)
    {
        this.message = message;
    }

    public void printMessage()
    {
        //TOGenerate log message
    }
}