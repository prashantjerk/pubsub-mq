package com.example.mqueuePS.pubsub;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile("receiver")
@Service
public class PubSubReceiver {

    private final String username;  // the username is set from the PubSubReceiver()
    private final Queue queue;      // the queue is set
    private final AmqpAdmin amqpAdmin;
    private final FanoutExchange fanoutExchange;

    // this is autowired meaning that it will instantiate itself based on the
    // parameters which are either beaned or provided automatically
    @Autowired
    public PubSubReceiver(int randomInt, Queue queue, AmqpAdmin amqpAdmin, FanoutExchange fanoutExchange) {
        this.username = "user" + randomInt; // randomInt is created using @Bean in the config class
        this.queue = queue;             // queue comes from the @Bean in the PubSubConfig
        this.amqpAdmin = amqpAdmin;     // amqpAdmin is automatically provided by the Spring Boot Application
        this.fanoutExchange = fanoutExchange;   // this is created in using @Bean in the config class
    }

    // it binds the queue to a fanoutExchange (it is same everytime given the bean is skeleton)
    @PostConstruct
    public void declareAndBind() {
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(fanoutExchange);
        amqpAdmin.declareBinding(binding);
    }

    @RabbitListener(queues = "#{@queue.name}")
    public void receive(String message) throws InterruptedException {
        System.out.println(message);
    }
}