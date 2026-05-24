package com.tatafoods.exceptions;

public class ItemNotFoundException extends RuntimeException{
	public ItemNotFoundException(String message) {
		super(message); 
	}

}
