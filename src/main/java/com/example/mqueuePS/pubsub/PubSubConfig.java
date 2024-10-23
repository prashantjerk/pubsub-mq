package com.example.mqueuePS.pubsub;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Random;

@Profile("pubsub")
@Configuration
@EnableRabbit
public class PubSubConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public int randomInt() {
        return new Random(System.currentTimeMillis()).nextInt(100);
    }

    @Bean
    public String queueName(@Value("#{@randomInt}") int randomInt) {
        return "user" + randomInt + ".queue";
    }

    @Bean
    public Queue queue(@Value("#{@queueName}") String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    public Binding binding(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public PubSubReceiver receiver(@Value("#{@randomInt}") int randomInt,
                                   Queue queue,
                                   AmqpAdmin amqpAdmin,
                                   FanoutExchange fanoutExchange) {
        return new PubSubReceiver(randomInt, queue, amqpAdmin, fanoutExchange);
    }zzz

    @Bean
    public PubSubSender sender(@Value("#{@randomInt}") int randomInt) {
        return new PubSubSender(randomInt);
    }
}