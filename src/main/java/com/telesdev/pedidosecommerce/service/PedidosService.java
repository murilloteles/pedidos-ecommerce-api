package com.telesdev.pedidosecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.telesdev.pedidosecommerce.domain.ItemPedido;
import com.telesdev.pedidosecommerce.domain.Pedido;
import com.telesdev.pedidosecommerce.exceptions.PedidoNaoEncontradoException;
import com.telesdev.pedidosecommerce.repository.PedidosRepository;

@Service
public class PedidosService {
	
	@Autowired
	PedidosRepository pedidosRepository;
	
	@Autowired
	ItensService itensService;

	public List<Pedido> listar() {
		return pedidosRepository.findAll();
	}

	public Pedido salvar(Pedido pedido) {
		List<ItemPedido> listaItemPedidos = new ArrayList<>();
		
		pedido.getItens().forEach(item -> {
			ItemPedido itemResultado = itensService.buscar(item.getNome());
			
			if(!isItemExistente(listaItemPedidos, item)) {
				itemResultado.setQuantidade(item.getQuantidade());
				itemResultado.setPedido(pedido);
				listaItemPedidos.add(itemResultado);
			}

		});
		pedido.getItens().clear();
		pedido.getItens().addAll(listaItemPedidos); 
		Pedido pedidoSalvo = pedidosRepository.save(pedido);
		return atualizarTotalPedido(pedidoSalvo);
	}

	private boolean isItemExistente(List<ItemPedido> listaItemPedidos, ItemPedido item) {
		return listaItemPedidos.stream().filter(i -> item.getNome().toUpperCase().equals(i.getNome().toUpperCase())).count() > 0;
	}

	public Pedido buscar(Long id) {
		Optional<Pedido> optionalPedido = pedidosRepository.findById(id);
		
		if(!optionalPedido.isPresent())
			throw new PedidoNaoEncontradoException("O Pedido ID " + id +" não foi encontrado");
		
		return optionalPedido.get();	
		
	}

	public void deletar(Long id) {
		try {
			pedidosRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new PedidoNaoEncontradoException("O Pedido ID " + id +" não foi encontrado");
		}
	}

	public void atualizar(Pedido pedido) {
		Pedido pedidoReturn = verificaSeExistePedido(pedido.getId());
		pedidoReturn.setDataCriacao(pedido.getDataCriacao()); //talvez não seja necessario
		pedidoReturn.getItens().clear();
		pedidoReturn.getItens().addAll(pedido.getItens());
		salvar(pedidoReturn);
	}
	
	private Pedido verificaSeExistePedido(Long id) {
		return buscar(id);
	}
	
	private Pedido atualizarTotalPedido(Pedido pedido) {
		//pedido.getItens().forEach(item -> pedido.setTotal( pedido.getTotal().add(item.getPreco().multiply(new BigDecimal(item.getQuantidade())))));
		//Só para exemplo, chamando a function pgsql
		pedido.setTotal(itensService.calcularValorTotalItensPedido(pedido.getId()));
		return pedidosRepository.save(pedido);
	}

}
