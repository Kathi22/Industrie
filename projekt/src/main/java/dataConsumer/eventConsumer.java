package dataConsumer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class eventConsumer extends Consumer {

	public eventConsumer(String exchangeName) {
		super(exchangeName);
	}
	
	public static void main(String[] argv) throws Exception {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare("logs", "fanout");
	    String queueName = channel.queueDeclare().getQueue();
	    channel.queueBind(queueName, "logs", "");
	    
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume(queueName, true, consumer);

	    while (true) {
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      String message = new String(delivery.getBody());

	      System.out.println(" [x] Received '" + message + "'");   
	    }
	  }

}
