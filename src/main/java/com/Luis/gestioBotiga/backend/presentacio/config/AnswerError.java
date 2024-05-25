package com.Luis.gestioBotiga.backend.presentacio.config;

import java.io.Serializable;

public class AnswerError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String error;
	
	public AnswerError (String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
