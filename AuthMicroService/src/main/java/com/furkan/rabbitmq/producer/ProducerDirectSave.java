package com.furkan.rabbitmq.producer;

import com.furkan.rabbitmq.messagemodel.ModelSave;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerDirectSave {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessageSaveDriver(ModelSave modelSave){
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-driver",modelSave);
    }
    public void sendMessageSavePassenger(ModelSave modelSave){
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-passenger",modelSave);
    }

}
