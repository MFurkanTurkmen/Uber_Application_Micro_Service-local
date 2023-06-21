package com.furkan.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.exchange.direct}")
    private String directExchange;


    // queue
    @Value("${rabbitmq.queue.register-passenger}")
    private String queueRegisterPassenger;
    @Value("${rabbitmq.queue.register-driver}")
    private String queueRegisterDriver;

    //key
    @Value("${rabbitmq.key.register-passenger}")
    private String keyRegisterPassenger;
    @Value("${rabbitmq.key.register-driver}")
    private String keyRegisterDriver;

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(directExchange);
    }


    // queue
    @Bean
    Queue queueRegisterPassenger (){
        return new Queue(queueRegisterPassenger);
    }
    @Bean
    Queue queueRegisterDriver(){
        return new Queue(queueRegisterDriver);
    }
    // key
    @Bean
    public Binding bindingRegisterPassenger(final Queue queueRegisterPassenger, final DirectExchange directExchange){
        return BindingBuilder.bind(queueRegisterPassenger).to(directExchange).with(keyRegisterPassenger);
    }
    @Bean Binding bindingRegisterDriver(final Queue queueRegisterDriver, final DirectExchange directExchange){
        return BindingBuilder.bind(queueRegisterDriver).to(directExchange).with(keyRegisterDriver);
    }



}
