package com.om.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.om.app.services.MqttMessageService;


@SpringBootApplication
public class OmInventory {
	@Autowired
	private MqttMessageService messagingService;
	@Autowired
	private ConfigurableApplicationContext context;
	public static void main(String[] args) {
		SpringApplication.run(OmInventory.class, args);
	}
	public void run(String... args) throws Exception {
		final String topic = "om/topic/event";

		messagingService.subscribe(topic);

		messagingService.publish(topic, "Hi\nThis is a sample message published to topic roytuts/topic/event", 0, true);

		context.close();
	}
}
