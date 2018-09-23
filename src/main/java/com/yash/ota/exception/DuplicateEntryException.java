package com.yash.ota.exception;

/**
 * This class provides a DuplicateEntryException when trainer enters already existing batch.
 *
 * @author Madhuri Vutukury
 */
public class DuplicateEntryException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateEntryException() {
    }

    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEntryException(Throwable cause) {
        super(cause);
    }


}
