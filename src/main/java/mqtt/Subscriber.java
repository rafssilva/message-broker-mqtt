package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class Subscriber implements MqttCallback {

	private final MqttClient client;
	private final String[] topics;

	// Reticências indica lista de argumentos de comprimento variável (no caso, o
	// subscriber pode se inscrever em um ou mais tópicos)
	// Esse uso de reticências deve ser usado no fim da lista de parâmetros e é
	// utilizado como um array do tipo especificado
	public Subscriber(final String url, final String... topics) throws MqttException {
		client = new MqttClient(url, MqttClient.generateClientId());
		client.setCallback(this); // Define o Subscriber como sendo seu próprio listener para novas mensagens
		this.topics = topics;
	}

	public void connect() throws MqttSecurityException, MqttException {
		client.connect();
		client.subscribe(topics); // Define o tópico de preferência do assinante para recebimento de mensagens
	}

	public void disconnect() throws MqttException {
		client.disconnect();
	}

	public void connectionLost(Throwable cause) {
		System.out.println("Connection to MQTT broker lost!");
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(
				"O Subscriber do tópico \"" + topic + "\" recebeu a mensagem:\n\t" + new String(message.getPayload()));
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
	}
}