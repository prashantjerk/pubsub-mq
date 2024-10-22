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

    public PubSubSender(int randomInt) {
        this.username = "user" + randomInt;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;

    public void send() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(username + ": ");
        String message;

        while(!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
//            User user = new User(username, message);
            rabbitTemplate.convertAndSend(fanout.getName(), "", message);
        }
        System.out.println("Exited");
        scanner.close();
    }
}
