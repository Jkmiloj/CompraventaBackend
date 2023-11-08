package com.BuyAndSell.Compraventa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.BuyAndSell.Compraventa")
public class CompraventaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompraventaApplication.class, args);
	}

}
