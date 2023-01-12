package com.telesdev.pedidosecommerce.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.telesdev.pedidosecommerce.domain.DetalhesErro;
import com.telesdev.pedidosecommerce.exceptions.ItemPedidoNaoEncontradoException;
import com.telesdev.pedidosecommerce.exceptions.PedidoNaoEncontradoException;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro>  handlerPedidoNaoEncontradoException(PedidoNaoEncontradoException e,
																						HttpServletRequest request) {
		
		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setTitulo(e.getMessage());
		detalhesErro.setStatus(404);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
		
	}
	
	@ExceptionHandler(ItemPedidoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro>  handlerItemPedidoNaoEncontradoException(ItemPedidoNaoEncontradoException e,
																						HttpServletRequest request) {
		
		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setTitulo(e.getMessage());
		detalhesErro.setStatus(404);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
		
	}
	
}
