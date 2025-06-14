package com.iiht.training.elibrary.exception;

public class BookAlreadyIssuedException extends RuntimeException {

	public BookAlreadyIssuedException() {
		super();
	}

	public BookAlreadyIssuedException(String message) {
		super(message);

	}

}
