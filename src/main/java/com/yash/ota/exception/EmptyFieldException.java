package com.yash.ota.exception;

/**
 * This class provides EmptyFieldException when trainer submits form without entering appropriate value.
 *
 * @author Madhuri Vutukury
 */
public class EmptyFieldException extends Exception {

    public EmptyFieldException() {
    }

    public EmptyFieldException(String message) {
        super(message);
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFieldException(Throwable cause) {
        super(cause);
    }



}

