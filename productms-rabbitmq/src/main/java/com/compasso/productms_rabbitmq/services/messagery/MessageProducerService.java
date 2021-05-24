package com.compasso.productms_rabbitmq.services.messagery;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {
	
    @Autowired
    private RabbitTemplate template;
    
    public void send(String queue, String message) {
        this.template.convertAndSend(queue, message);
        System.out.println(" -> [x] Mensagem enviada: '" + message + "'");
    }
}
