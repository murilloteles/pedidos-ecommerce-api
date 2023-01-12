package com.telesdev.pedidosecommerce.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
