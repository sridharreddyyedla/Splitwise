package com.splitwise.exceptions.validation;

import com.splitwise.exceptions.SplitwiseException;

public class ValidationException extends SplitwiseException {
	public ValidationException(String message) {
		super(message);
	}
}
