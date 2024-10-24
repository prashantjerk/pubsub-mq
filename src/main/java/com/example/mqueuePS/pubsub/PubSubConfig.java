package com.example.mqueuePS.pubsub;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Profile("pubsub")
@Configuration
@EnableRabbit
public class PubSubConfig {

    // fanout exchange is beaned
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("fanoutExchange");
    }

    // this is a bean for a random integer value
    @Bean
    @Scope("prototype") // this means everytime a new random integer is instantiated
    public int randomInt() {
        return new Random(System.currentTimeMillis()).nextInt(100);
    }

    // this creates a new queue with new int everytime it is called eg: pubsubQ20, pubsubQ10
    @Bean
    public Queue queue(int randomInt) {
        return new Queue("pubsubQ" + randomInt, true);
    }
}