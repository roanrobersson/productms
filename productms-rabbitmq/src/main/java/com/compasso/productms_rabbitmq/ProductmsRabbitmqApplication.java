package com.compasso.productms_rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductmsRabbitmqApplication {
	
    public static void main(String[] args) {
		SpringApplication.run(ProductmsRabbitmqApplication.class, args);
	}
    
    @Bean
    public Queue queue() {
        return new Queue("products_queue");
    }
}
