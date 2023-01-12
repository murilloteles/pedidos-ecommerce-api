package com.telesdev.pedidosecommerce.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telesdev.pedidosecommerce.domain.ItemPedido;

public interface ItensRepository extends JpaRepository<ItemPedido, Long> {

	@Query(nativeQuery = true, value = "SELECT valor_total_pedido(:idPedido)")
	BigDecimal calcularValorTotalItensPedido(@Param("idPedido") Long idPedido);

}