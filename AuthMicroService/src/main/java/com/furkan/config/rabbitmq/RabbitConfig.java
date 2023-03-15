package com.furkan.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // exchange
    private String exchangeDirect = "exchange-direct";
    private String exchangeFanout = "exchange-fanout";
    private String exchangeTopic = "exchange-topic";


    // Key
    private String bindingKeyDriver = "binding-key-driver";
    private String bindingKeyPassenger = "binding-key-passenger";


    // Queu
    private String queueCreatePassenger = "queue-create-passenger";
    private String queueCreateDriver = "queue-create-driver";
    private String queueFaonutDriver = "queue-fanout-driver";
    private String queueFaonutPassenger = "queue-fanout-passenger";


    /**
     * ---- Exchange ----
     */
    @Bean
    DirectExchange exchangeDirect() {
        return new DirectExchange(exchangeDirect);
    }

    @Bean
    FanoutExchange exchangeFanout() {
        return new FanoutExchange(exchangeFanout);
    }

    @Bean
    TopicExchange exchangeTopic() {
        return new TopicExchange(exchangeTopic);
    }

    /**
     * ---- Queu ----
     */
    @Bean
    Queue queueCreateDriver() {
        return new Queue(queueCreateDriver);
    }

    @Bean
    Queue queueCreatePassenger() {
        return new Queue(queueCreatePassenger);
    }

    @Bean
    Queue queueFanoutPassenger() {
        return new Queue(queueFaonutPassenger);
    }

    @Bean
    Queue queueFanoutDriver() {
        return new Queue(queueFaonutDriver);
    }


    /**
     * ---- Binding ----
     */
    @Bean
    public Binding bindingCreateDriver(final Queue queueCreateDriver, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueCreateDriver).to(directExchange).with(bindingKeyDriver);
    }

    @Bean
    public Binding bindingCreatePassenger(final Queue queueCreatePassenger, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueCreatePassenger).to(directExchange).with(bindingKeyPassenger);
    }

    @Bean
    public Binding bindingFanoutDriver(final Queue queueFanoutDriver, final FanoutExchange exchangeFanout) {
        return BindingBuilder.bind(queueFanoutDriver).to(exchangeFanout);
    }

    @Bean
    public Binding bindingFanoutPassenger(final Queue queueFanoutPassenger, final FanoutExchange exchangeFanout) {
        return BindingBuilder.bind(queueFanoutPassenger).to(exchangeFanout);
    }

}