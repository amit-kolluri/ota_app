/*
 * QueryException
 *
 * Version 0.1
 *
 * Date: 08/23/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.exception;
/**
 * QueryException thrown when a query fails
 * @author caleb.reiter, connor.brown
 *
 */
public class InvalidQueryException extends Exception {
    public InvalidQueryException() {
        super();
    }
    
    public InvalidQueryException(String errMsg) {
    	super(errMsg);
    }
}
