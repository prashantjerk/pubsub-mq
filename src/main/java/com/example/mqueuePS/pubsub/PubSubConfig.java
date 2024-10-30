package com.example.mqueuePS.pubsub;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("pubsub")
@Configuration
public class PubSubConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }


    // the queue for stock related message for customers
    @Bean
    public Queue queue() {
        return new AnonymousQueue();
    }
}