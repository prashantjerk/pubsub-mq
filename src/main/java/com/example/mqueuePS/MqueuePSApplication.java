package com.example.mqueuePS;

import com.example.mqueuePS.pubsub.PubSubReceiver;
import com.example.mqueuePS.pubsub.PubSubSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MqueuePSApplication {
	private Environment env;

	@Profile("sender")
	@Bean
	public CommandLineRunner execute(PubSubSender sender) {
		return args -> {
//			if(args.length > 0) {
//				String username = args[0];
//
//				String[] activeProfiles = env.getActiveProfiles();
//
//				if("sender".equalsIgnoreCase(activeProfiles[0])) {
//					sender.setUsername(username);
//					sender.send();
//				} else if("receiver".equalsIgnoreCase(activeProfiles[0])) {
//					receiver.setUsername(username);
//				}
//			} else {
//				System.out.println("Please provide the username: ");
//			}
		sender.send();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MqueuePSApplication.class, args);
	}
}
