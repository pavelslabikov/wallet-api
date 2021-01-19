package com.company.core.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.METHOD_NOT_ALLOWED, reason="Account already exist")
public class AccountAlreadyExistException extends RuntimeException {
}
