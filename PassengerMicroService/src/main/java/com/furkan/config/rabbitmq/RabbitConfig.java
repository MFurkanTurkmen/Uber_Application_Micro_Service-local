package com.furkan.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // exchange
    private String exchangeDirect = "exchange-direct";


    // Key
    private String bindingKeySetExpDriver = "binding-key-set-exp-driver";
    private String bindingKeyStartRide= "binding-key-start-ride";

    // Queu
    private String queueSetExpDriver = "queue-set-exp-driver";
    private String queueStartRide="queue-start-ride";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(exchangeDirect);
    }

    @Bean
    Queue queueSetExpDriver() {
        return new Queue(queueSetExpDriver);
    }

    @Bean
    Queue queueStartRide(){
        return new Queue(queueStartRide);
    }
    @Bean
    public Binding bindingSetExpDriver(final Queue queueSetExpDriver, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueSetExpDriver).to(directExchange).with(bindingKeySetExpDriver);
    }
     @Bean
    public Binding bindingStartRide(final Queue queueStartRide,final DirectExchange directExchange){
        return BindingBuilder.bind(queueStartRide).to(directExchange).with(bindingKeyStartRide);
    }


}