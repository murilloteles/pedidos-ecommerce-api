package com.telesdev.pedidosecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PedidosEcommerceApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PedidosEcommerceApiApplication.class, args);
	}
	
}
