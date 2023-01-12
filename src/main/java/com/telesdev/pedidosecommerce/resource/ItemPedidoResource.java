package com.telesdev.pedidosecommerce.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telesdev.pedidosecommerce.domain.ItemPedido;
import com.telesdev.pedidosecommerce.service.ItensService;

@RestController
@RequestMapping("/itens")
public class ItemPedidoResource {
	
	@Autowired
	ItensService itensService;
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(itensService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable("nome") String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(itensService.buscar(nome));
	}
}
