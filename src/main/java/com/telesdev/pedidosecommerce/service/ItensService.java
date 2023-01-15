package com.telesdev.pedidosecommerce.service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.telesdev.pedidosecommerce.consumer.ConsumerKafka;
import com.telesdev.pedidosecommerce.domain.ItemPedido;
import com.telesdev.pedidosecommerce.exceptions.ItemPedidoNaoEncontradoException;
import com.telesdev.pedidosecommerce.repository.ItensRepository;

@Service
public class ItensService {
	
	@Autowired
	ItensRepository itensRepository;
	
	@Autowired
	ConsumerKafka consumerKafka;
	
	String itens;

	public ItemPedido buscar(String nome) {
		List<ItemPedido> todosItens = listarTodosItens();
		ItemPedido itemPedido = todosItens.stream()
				.filter(item -> item.getNome().toUpperCase().equals(nome.toUpperCase()))
				.findAny()
				.orElse(null);
		
		if(itemPedido == null)
			throw new ItemPedidoNaoEncontradoException("O Item:" + nome + " não foi encontrado");
		
		return itemPedido;	
	}
	
	public BigDecimal calcularValorTotalItensPedido(Long idPedido) {
		return itensRepository.calcularValorTotalItensPedido(idPedido);
	}
	
	private  List<ItemPedido> listarTodosItens() {
		if(consumerKafka.getListItensDisponiveis() != null && !consumerKafka.getListItensDisponiveis().isEmpty()) {
			return consumerKafka.getListItensDisponiveis();
		}
		
		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/itens")).build();

		ResponseEntity<ItemPedido[]> response = restTemplate.exchange(request, ItemPedido[].class);

		return Arrays.asList(response.getBody());
			
	}

}
