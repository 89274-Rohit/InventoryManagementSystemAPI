package com.sunbeam.exception;

public class InvalidStockOperationException extends RuntimeException{
	public InvalidStockOperationException(String msg) {
		super(msg);
	}
}
