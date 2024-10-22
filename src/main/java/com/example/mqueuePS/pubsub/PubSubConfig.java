package com.example.mqueuePS.pubsub;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Random;

@Profile("pubsub")
@Configuration
public class PubSubConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public int randomInt() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(100); // Generate random number under 100
    }


    @Bean
    public PubSubReceiver receiver() {
        Random random = new Random(System.currentTimeMillis());
        return new PubSubReceiver(randomInt());
    }

    @Bean
    public PubSubSender sender() {
        Random random = new Random(System.currentTimeMillis());
        return new PubSubSender(randomInt());
    }
}
