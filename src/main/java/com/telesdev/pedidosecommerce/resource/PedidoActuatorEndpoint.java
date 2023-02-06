package com.telesdev.pedidosecommerce.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.telesdev.pedidosecommerce.repository.PedidosRepository;

@RestControllerEndpoint(id="pedidos")
@Controller
public class PedidoActuatorEndpoint {

	@Autowired
	private PedidosRepository pedidosRepository;
	
	@GetMapping
	public List<String> pedidoRaiz(){
		return Arrays.asList("pedidos-contagem");
	}
	
	@GetMapping("/pedidos-contagem")
	public Map<String, Object> pedidoContagem(){
		Map<String, Object> resposta = new HashMap<>();
		resposta.put("quantidade", pedidosRepository.count());
		return resposta;
	}
}
