package com.telesdev.pedidosecommerce.actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.telesdev.pedidosecommerce.repository.PedidosRepository;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

@Configuration
public class MetricasCustomizacaoConfig {

	@Bean
	public MeterBinder quantidadePedidos(PedidosRepository pedidosRepository) {
		return (meterRegistry) -> {
			Gauge.builder("pedido.qtd", pedidosRepository::count).register(meterRegistry);
		};
	}
	
	@Bean
	public CountedAspect countedAspect(MeterRegistry meterRegistry) {
		return new CountedAspect(meterRegistry);
	}
}
