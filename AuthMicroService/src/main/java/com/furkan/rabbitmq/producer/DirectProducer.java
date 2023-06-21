package com.furkan.rabbitmq.producer;

import com.furkan.rabbitmq.model.ModelRegisterDriver;
import com.furkan.rabbitmq.model.ModelRegisterPassenger;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectProducer {

    private final RabbitTemplate rabbitTemplate;

    // exchanges
    @Value("${rabbitmq.exchange.direct}")
    private  String directExchange;

    // keys
    @Value("${rabbitmq.key.register-driver}")
    private  String keyRegisterDriver;
    @Value("${rabbitmq.key.register-passenger}")
    private  String keyRegisterPassenger;

    public void sendRegisterPassenger(ModelRegisterPassenger model){
        rabbitTemplate.convertAndSend(directExchange,keyRegisterPassenger,model);
    }
    public void sendRegisterDriver(ModelRegisterDriver model){
        rabbitTemplate.convertAndSend(directExchange,keyRegisterDriver,model);

    }
}
