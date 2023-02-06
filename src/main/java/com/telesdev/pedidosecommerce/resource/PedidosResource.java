package com.telesdev.pedidosecommerce.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.telesdev.pedidosecommerce.domain.Pedido;
import com.telesdev.pedidosecommerce.service.PedidosService;

import io.micrometer.core.annotation.Counted;

@RestController
@RequestMapping("/pedidos")
public class PedidosResource {
	
	@Autowired
	PedidosService pedidosService;
	
	@GetMapping
	@Counted(value= "pedido.count.listar")
	public ResponseEntity<List<Pedido>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(pedidosService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Pedido pedido) {
		pedido = pedidosService.salvar(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(pedido.getId())
						.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(pedidosService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id,
						   				  @Valid @RequestBody Pedido pedido) {
		
			pedido.setId(id);
			pedidosService.atualizar(pedido);	
			return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
			pedidosService.deletar(id);
			return ResponseEntity.noContent().build();
	}
}
