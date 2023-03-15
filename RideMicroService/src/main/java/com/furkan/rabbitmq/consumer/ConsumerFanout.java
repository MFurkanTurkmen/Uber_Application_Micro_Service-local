package com.furkan.rabbitmq.consumer;

import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerFanout {
    private final RideService rideService;

    @RabbitListener(queues = "queue-fanout-driver")
    public void listenerMessage(ModelFanout modelFanout){
        rideService.getMessage(modelFanout);
    }
}
