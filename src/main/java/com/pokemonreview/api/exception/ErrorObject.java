package com.pokemonreview.api.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {
	private Integer statusCode;
	private String message;
	private String details;
	private Date timeStamp;
}
