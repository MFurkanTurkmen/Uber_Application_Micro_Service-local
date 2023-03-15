package com.furkan.rabbitmq.producer;

import com.furkan.rabbitmq.messagemodel.ModelFanout;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerFanoutMessage {
    private final RabbitTemplate rabbitTemplate;

    public void sendFanoutMessage(ModelFanout modelFanout){
        rabbitTemplate.convertAndSend("exchange-fanout","",modelFanout);
    }
}
