package com.mystore.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PayOrderException extends RuntimeException {

	public PayOrderException(Long orderId) {
		super("Order with id " + orderId + " cannot be paid");
	}

	public PayOrderException(String message) {
		super(message);
	}
}
