/*
 * InvalidIdException
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.exception;
/**
 * InvalidIdException throws when an invalid user id is given
 * @author caleb.reiter scotrenz
 *
 */
public class InvalidIdException extends Exception {
    public InvalidIdException() {
        super();
    }
    
    public InvalidIdException(String errMsg) {
    	super(errMsg);
    }
}
