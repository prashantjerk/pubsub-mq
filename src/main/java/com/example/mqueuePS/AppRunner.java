//package com.example.mqueuePS;
//
//import com.example.mqueuePS.pubsub.PubSubReceiver;
//import com.example.mqueuePS.pubsub.PubSubSender;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.util.Scanner;
//
//@Profile("pubsub")
//@Component
//public class AppRunner implements CommandLineRunner {
//
//    @Autowired
//    private PubSubSender sender;
//
//    @Autowired
//    private PubSubReceiver receiver;
//
//    @Override
//    public void run(String... args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your username: ");
//        String username = scanner.nextLine();
//
//        // Set the username for sender and receiver
//        sender.setUsername(username);
//        receiver.setUsername(username);
//
//        // Start sending or receiving messages as needed
//        new Thread(sender::send).start();
//
//        // Close the scanner to avoid resource leaks
//        scanner.close();
//    }
//}
