package com.pokemonreview.api.exception;

public class ReviewNotFoundException extends RuntimeException{
	
	private static final long serialVersionId = 2;
	public ReviewNotFoundException(String message) {
		super(message);
	}
}
