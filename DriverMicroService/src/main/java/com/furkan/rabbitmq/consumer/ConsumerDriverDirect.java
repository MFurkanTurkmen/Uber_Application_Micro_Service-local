package com.furkan.rabbitmq.consumer;

import com.furkan.rabbitmq.messagemodel.ModelExpDriver;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerDriverDirect {
    private final DriverService driverService;

    @RabbitListener(queues = "queue-create-driver")
    public void listenerSaveDriver(ModelSave modelSave){
        driverService.save(modelSave);
    }

    @RabbitListener(queues = "queue-set-exp-driver")
    public void listenerSetExpDriver(ModelExpDriver modelExpDriver){
        driverService.updateDriverExp(modelExpDriver);

    }
}
