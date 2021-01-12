package mqtt;

// Message Broker com Eclipse Mosquitto (MQTT protocol)
public class Main {

	public static void main(String[] args) throws Exception {

		// Outras URLs para teste "tcp://mqtt.fluux.io:1883"
		// "tcp://test.mosquitto.org:1883" "tcp://mqtt.teserakt.io:1883"
		final String url = "tcp://mqtt.fluux.io:1883";
		final String topic = "greeting"; // Os t�picos s�o case sensitive

		Publisher p = new Publisher(url);
		Subscriber s = new Subscriber(url, topic); // URL e o t�pico de sua prefer�ncia para recebimento de mensagens

		// Subscriber inscrito em mais de um t�pico (String... topics)
		Subscriber s2 = new Subscriber(url, topic, "rain");

		p.connect();
		s.connect();
		s2.connect();

		p.message("greeting", "hello"); // publicar(t�pico, mensagem)
		p.message("rain", "its raining");

		p.disconnect();
		s.disconnect();
		s2.disconnect();
	}
}