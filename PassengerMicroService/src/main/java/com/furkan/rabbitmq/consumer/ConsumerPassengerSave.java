package com.furkan.rabbitmq.consumer;

import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerPassengerSave {
    private final PassengerService passengerService;

    @RabbitListener(queues = "queue-create-passenger")
    public void listenerSaveDriver(ModelSave modelSave){
        passengerService.save(modelSave);
    }
}
