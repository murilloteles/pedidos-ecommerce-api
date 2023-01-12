package com.telesdev.pedidosecommerce.exceptions;

public class ItemPedidoNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ItemPedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
