package com.bookstore.exception;

public class BookIsBorrowedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookIsBorrowedException() {
        super();
    }

    public BookIsBorrowedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BookIsBorrowedException(final String message) {
        super(message);
    }

    public BookIsBorrowedException(final Throwable cause) {
        super(cause);
    }
}
