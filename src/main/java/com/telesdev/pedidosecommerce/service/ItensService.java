package com.telesdev.pedidosecommerce.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telesdev.pedidosecommerce.domain.ItemPedido;
import com.telesdev.pedidosecommerce.exceptions.ItemPedidoNaoEncontradoException;
import com.telesdev.pedidosecommerce.repository.ItensRepository;

@Service
public class ItensService {
	
	@Autowired
	private ItensRepository itensRepository;

	public List<ItemPedido> listar() {
		return listarTodosItens();
	}

	public ItemPedido buscar(String nome) {
		ItemPedido itemPedido = listarTodosItens().stream()
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
		ItemPedido noteBook = new ItemPedido();
		noteBook.setNome("NoteBook");
		noteBook.setPreco(new BigDecimal(4000.00));
		
		ItemPedido geladeira = new ItemPedido();
		geladeira.setNome("Geladeira");
		geladeira.setPreco(new BigDecimal(3000.00));
		
		ItemPedido televisao = new ItemPedido();
		televisao.setNome("Televisão");
		televisao.setPreco(new BigDecimal(3500.00));
		
		ItemPedido freezer = new ItemPedido();
		freezer.setNome("Freezer");
		freezer.setPreco(new BigDecimal(2000.00));
		
		ItemPedido microondas = new ItemPedido();
		microondas.setNome("Micro-ondas");
		microondas.setPreco(new BigDecimal(2500.00));
		
		return Arrays.asList(noteBook,geladeira,televisao,freezer,microondas);
	}

}
