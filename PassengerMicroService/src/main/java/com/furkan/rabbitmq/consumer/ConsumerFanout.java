package com.furkan.rabbitmq.consumer;

import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerFanout {
    private final PassengerService passengerService;

    @RabbitListener(queues = "queue-fanout-passenger")
    public void listenerMessage(ModelFanout modelFanout){
        passengerService.getMessage(modelFanout);
    }
}
