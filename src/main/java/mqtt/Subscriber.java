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

	// Retic�ncias indica lista de argumentos de comprimento vari�vel (no caso, o
	// subscriber pode se inscrever em um ou mais t�picos)
	// Esse uso de retic�ncias deve ser usado no fim da lista de par�metros e �
	// utilizado como um array do tipo especificado
	public Subscriber(final String url, final String... topics) throws MqttException {
		client = new MqttClient(url, MqttClient.generateClientId());
		client.setCallback(this); // Define o Subscriber como sendo seu pr�prio listener para novas mensagens
		this.topics = topics;
	}

	public void connect() throws MqttSecurityException, MqttException {
		client.connect();
		client.subscribe(topics); // Define o t�pico de prefer�ncia do assinante para recebimento de mensagens
	}

	public void disconnect() throws MqttException {
		client.disconnect();
	}

	public void connectionLost(Throwable cause) {
		System.out.println("Connection to MQTT broker lost!");
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(
				"O Subscriber do t�pico \"" + topic + "\" recebeu a mensagem:\n\t" + new String(message.getPayload()));
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
	}
}