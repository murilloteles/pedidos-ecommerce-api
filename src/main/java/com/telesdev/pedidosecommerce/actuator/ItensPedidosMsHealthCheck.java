package com.telesdev.pedidosecommerce.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.telesdev.pedidosecommerce.service.ItensService;

@Component("itensMsHealth")
public class ItensPedidosMsHealthCheck implements HealthIndicator {
	
	@Autowired
	ItensService itensService;
	
	@Override
	public Health health() {
		if(itensService.msEstaOnline()) return Health.up().withDetail("mensagem", "Comunicação OK").build();
		
		return Health.down().withDetail("mensagem", "Comunicação Falhou").build();
	}

}
