package com.furkan.rabbitmq.consumer;

import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerFanout {
    private final DriverService driverService;

    @RabbitListener(queues = "queue-fanout-driver")
    public void listenerMessage(ModelFanout modelFanout){
        driverService.getMessage(modelFanout);
    }
}
