package com.example.mqueuePS.pubsub;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Profile("sender")
@Service
public class PubSubSender {
    private  String username;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;

    @Autowired
    private int randomInt;

//    private final int constRandomInt = randomInt;

    public void send() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("user" + randomInt);
        String message;

        while(!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
            rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
        }
        System.out.println("Exited");
        scanner.close();
    }
}
