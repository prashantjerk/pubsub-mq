package com.example.mqueuePS.pubsub;

//import com.example.mqueuePS.pubsub.User;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("receiver")
@Service
public class PubSubReceiver {

    private final String username;
    private final Queue queue;
    private final AmqpAdmin amqpAdmin;
    private final FanoutExchange fanoutExchange;

    public PubSubReceiver(int randomInt,
                          Queue queue,
                          AmqpAdmin amqpAdmin,
                          FanoutExchange fanoutExchange) {
        this.username = "user" + randomInt;
        this.queue = queue;
        this.amqpAdmin = amqpAdmin;
        this.fanoutExchange = fanoutExchange;
    }

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