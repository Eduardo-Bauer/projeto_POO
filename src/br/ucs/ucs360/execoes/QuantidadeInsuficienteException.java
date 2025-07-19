package br.ucs.ucs360.execoes;

public class QuantidadeInsuficienteException extends Exception {
	
	public static final String MESSAGE = "Quantidade insuficiente no estoque";

	public QuantidadeInsuficienteException() {
		super(MESSAGE);
	}

	public QuantidadeInsuficienteException(String message) {
		super(MESSAGE + " " + message);
	}

	public QuantidadeInsuficienteException(Throwable cause) {
		super(MESSAGE, cause);
	}

	public QuantidadeInsuficienteException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}

	public QuantidadeInsuficienteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(MESSAGE + " " + message, cause, enableSuppression, writableStackTrace);
	}
}
