package com.telesdev.pedidosecommerce.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telesdev.pedidosecommerce.domain.ItemPedido;
import com.telesdev.pedidosecommerce.domain.Pedido;
import com.telesdev.pedidosecommerce.exceptions.ItemPedidoNaoEncontradoException;
import com.telesdev.pedidosecommerce.exceptions.PedidoNaoEncontradoException;
import com.telesdev.pedidosecommerce.repository.PedidosRepository;
import com.telesdev.pedidosecommerce.service.ItensService;
import com.telesdev.pedidosecommerce.service.PedidosService;

@ExtendWith(MockitoExtension.class)
public class PedidosResourceTest {
    
    @Mock
    private PedidosRepository pedidosRepositoryMock;
    
    @InjectMocks
    private PedidosService pedidosService;

    @Mock
    private ItensService itensServiceMock;

    @Test
    public void deveLancarExcecaoQuandoBuscaPedidoNaoExistente() {
    	Optional<Pedido> optionalPedido = Optional.empty();

        when(pedidosRepositoryMock.findById(0L)).thenReturn(optionalPedido);

        assertThrows(PedidoNaoEncontradoException.class,() -> pedidosService.buscar(0L));
    }
    
    @Test
    public void deveRetornarPedidoQuandoBuscar() {
    	
    	Pedido pedidoPopulado = getPedidoPopulado();
    	Optional<Pedido> optionalPedidoVazio = Optional.of(pedidoPopulado);

        when(pedidosRepositoryMock.findById(1L)).thenReturn(optionalPedidoVazio);

        assertEquals(pedidoPopulado,pedidosService.buscar(1L));
    }
    
    @Test
    public void deveLancarExcecaoQuandoForAtualizaPedidoNaoExistente() {
    	Optional<Pedido> optionalPedidoVazio = Optional.empty();
    	Pedido pedido = new Pedido();
    	pedido.setId(0L);

        when(pedidosRepositoryMock.findById(pedido.getId())).thenReturn(optionalPedidoVazio);

        assertThrows(PedidoNaoEncontradoException.class,() -> pedidosService.atualizar(pedido));
    }
    
    @Test
    public void deveLancarExcecaoQuandoForSalvarPedidoComItemNaoExistente() {
    	Pedido pedido = getPedidoPopulado();

        when(itensServiceMock.buscar(pedido.getItens().get(0).getNome())).thenThrow(ItemPedidoNaoEncontradoException.class);

        assertThrows(ItemPedidoNaoEncontradoException.class,()-> pedidosService.salvar(pedido));
    }
    
    @Test
    public void deveRetornarPedidoSalvoQuandoForSalvar() {
    	Pedido pedido = getPedidoPopulado();

        when(itensServiceMock.buscar(pedido.getItens().get(0).getNome())).thenReturn(pedido.getItens().get(0));
        when(itensServiceMock.calcularValorTotalItensPedido(pedido.getId())).thenReturn(pedido.getTotal());
        when(pedidosRepositoryMock.save(pedido)).thenReturn(pedido);

        assertEquals(pedido,pedidosService.salvar(pedido));
    }
    
    

	private Pedido getPedidoPopulado() {
		Pedido pedido = new Pedido();
		ItemPedido televisao = new ItemPedido();
		televisao.setId(1L);
		televisao.setNome("Televisão");
		televisao.setPreco(new BigDecimal(3500.00));
		televisao.setQuantidade(2L);
		List<ItemPedido> listItens = new ArrayList<>();
		listItens.add(televisao);
		
    	
    	pedido.setId(1L);
    	pedido.setItens(listItens);
    	pedido.setDataCriacao(new Date());
    	pedido.setTotal(new BigDecimal(7000.00));
    	return pedido;
	}
}