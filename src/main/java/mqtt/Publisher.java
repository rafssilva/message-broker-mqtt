package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class Publisher {

	private final MqttClient client;
	private final MqttMessage message;

	public Publisher(final String url) throws MqttException {
		client = new MqttClient(url, MqttClient.generateClientId());
		message = new MqttMessage();
	}

	public void connect() throws MqttSecurityException, MqttException {
		System.out.println("Publisher conectando...");
		client.connect();
	}

	public void message(final String topic, final String message) throws MqttPersistenceException, MqttException {
		System.out.println("Publicando mensagem no tópico: " + topic);
		this.message.setPayload(message.getBytes());
		client.publish(topic, this.message);
	}

	public void disconnect() throws MqttException {
		System.out.println("Publisher desconectando...");
		client.disconnect();
	}
}