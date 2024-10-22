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

    private String username;
    private Queue queue;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private FanoutExchange fanoutExchange;


    public PubSubReceiver(int randomInt) {
        this.username = "user" + randomInt;
        this.queue = createQueue(username);
    }

    @PostConstruct
    public void declareAndBind() {
        this.queue = createQueue(username);
        amqpAdmin.declareQueue(this.queue);
        Binding binding = BindingBuilder.bind(this.queue).to(fanoutExchange);
        amqpAdmin.declareBinding(binding);
    }

    public Queue createQueue(String username) {
        String queueName = username + ".queue";
        return new Queue(queueName, true);
    }

    @RabbitListener(queues = "#{@createQueue(username).name}")
    public void receive(User sender) throws InterruptedException {
        System.out.println(sender.username + ":" + sender.message);
    }
}
