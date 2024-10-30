package com.example.mqueuePS;

import com.example.mqueuePS.pubsub.PubSubSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class MqueuePSApplication {

	@Profile("sender")
	@Bean
	public CommandLineRunner execute(PubSubSender sender) {
		return args -> {
		sender.send();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MqueuePSApplication.class, args);
	}
}
