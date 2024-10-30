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

    @Autowired
    private AmqpAdmin amqpAdmin; // Admin to manage AMQP resources
    private final FanoutExchange fanoutExchange; // The exchange
    private final Queue messageQ; // The queue

    @Autowired
    public PubSubReceiver(FanoutExchange fanoutExchange, Queue messageQ) {
        this.fanoutExchange = fanoutExchange;
        this.messageQ = messageQ; // Inject the queue
    }

    @PostConstruct
    public void declareAndBind() {
        amqpAdmin.declareQueue(messageQ);

        // Bind the queue to the fanout exchange
        Binding binding = BindingBuilder.bind(messageQ).to(fanoutExchange);
        amqpAdmin.declareBinding(binding);
    }

    @RabbitListener(queues = "#{@queue.name}")
    public void receiveMessage(String message) {
        System.out.println("Update: " + message);
    }
}
