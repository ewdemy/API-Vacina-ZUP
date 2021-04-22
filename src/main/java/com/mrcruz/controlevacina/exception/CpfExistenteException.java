package com.mrcruz.controlevacina.exception;

public class CpfExistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CpfExistenteException(String message) {
		super(message);
	}

}
