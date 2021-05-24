package com.compasso.productms_rabbitmq.services.messagery;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {
	
	@RabbitListener(queues = "products_queue")
	public void listen(String message) {
	    System.out.println(" <- [x] Mensagem recebida: " + message);
	}
}
