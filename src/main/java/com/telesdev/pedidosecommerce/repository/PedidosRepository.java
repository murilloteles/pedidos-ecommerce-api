package com.telesdev.pedidosecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telesdev.pedidosecommerce.domain.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Long> {

}