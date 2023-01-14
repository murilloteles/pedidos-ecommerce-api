package com.telesdev.pedidosecommerce.consumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.telesdev.pedidosecommerce.domain.ItemPedido;

@Service
public class ConsumerKafka {
	
	private List<ItemPedido> listItensDisponiveis;
	
	@KafkaListener(topics = "topico.itens", groupId = "group_id")
	public void consumeKafkaItens(String jsonItens) throws IOException {
		Gson gson = new Gson();
		ItemPedido[] userArray = gson.fromJson(jsonItens, ItemPedido[].class);
		setListItensDisponiveis(Arrays.asList(userArray));
	}

	public List<ItemPedido> getListItensDisponiveis() {
		return listItensDisponiveis;
	}

	public void setListItensDisponiveis(List<ItemPedido> listItensDisponiveis) {
		this.listItensDisponiveis = listItensDisponiveis;
	}

}
