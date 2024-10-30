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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;

    public void send() {
        Scanner scanner = new Scanner(System.in);
        String message;

        while (true) {
            System.out.println("Enter your message (type 'exit' to quit):");

            message = scanner.nextLine();

            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Exited");
                break;
            }

            // Send the message to the fanout exchange
            rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
            System.out.println("Sent message to all queues: " + message);
        }

        scanner.close();
    }

}
