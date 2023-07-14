package com.om.app.Mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {
	private static final Logger logger=LoggerFactory.getLogger(MqttConfig.class);
	@Bean

	public MqttClient mqttClient() throws Exception {

	MqttClient	 mqttClient = new MqttClient("ssl://a9200cae7afe4dacb4b463781a68c576.s2.eu.hivemq.cloud:8883", "MqttcLient");

	MqttConnectOptions connectOptions = new MqttConnectOptions();

	connectOptions.setUserName("OMVPATEL");

	connectOptions.setPassword("Omv@8080".toCharArray());

	mqttClient.connect(connectOptions);

	if(mqttClient.isConnected()) {

	logger.info("Client Connected with Broker");

	mqttClient.subscribe("device/topic");

	}

	return mqttClient;
	}
}
