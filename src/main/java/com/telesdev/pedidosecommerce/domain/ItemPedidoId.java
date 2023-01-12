package com.telesdev.pedidosecommerce.domain;

public class ItemPedidoId {
	private Long pedidoId;
	private Long itemPedidoId;
	
	public ItemPedidoId(Long pedidoId, Long itemPedidoId) {
		super();
		this.pedidoId = pedidoId;
		this.itemPedidoId = itemPedidoId;
	}
	
	public ItemPedidoId() {
		super();
	}

	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Long getItemPedidoId() {
		return itemPedidoId;
	}
	public void setItemPedidoId(Long itemPedidoId) {
		this.itemPedidoId = itemPedidoId;
	}
	
	
}
