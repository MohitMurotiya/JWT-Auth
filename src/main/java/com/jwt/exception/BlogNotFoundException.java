package com.jwt.exception;

public class BlogNotFoundException extends Exception {

	private static final long serialVersionUID = -9185252707089561953L;

	public BlogNotFoundException(String message) {
		super(message);
	}

}
