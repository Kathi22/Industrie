package dataConsumer;

import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class EventConsumer extends Consumer {
	public EventConsumer(String exchangeName) throws IOException {
		super(exchangeName);
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.exchangeDeclare(exchangeName, "fanout");
	    String queueName = channel.queueDeclare().getQueue();
	    channel.queueBind(queueName, exchangeName, "");
	    
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	    this.setConsumer(new QueueingConsumer(channel));
	    channel.basicConsume(queueName, true, this.getConsumer());
	  }

	@Override
	public void process(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		  while (true) {
		      QueueingConsumer.Delivery delivery;
			try {
				delivery = this.getConsumer().nextDelivery();
				String message = new String(delivery.getBody());
			    System.out.println(" [x] Received '" + message + "'");   
			    process(message); 
			} catch (ShutdownSignalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConsumerCancelledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		    }
	}

}
