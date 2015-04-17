package dataConsumer;

import java.io.IOException;
import java.io.ObjectStreamException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.thoughtworks.xstream.XStream;

import de.dhbw.mannheim.erpsim.model.CustomerOrder;
import de.dhbw.mannheim.erpsim.model.MachineOrder;

public class ERPConsumer extends Consumer
{
	String exchangeName;
	XStream xstream = new XStream();

	public ERPConsumer(String exchangeName) throws IOException
	{
		super(exchangeName);
		this.exchangeName = exchangeName;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(exchangeName, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, exchangeName, "");

		System.out.println("[*] ERP-Consumer(" + exchangeName + "): Waiting for messages.");

		this.setConsumer(new QueueingConsumer(channel));
		channel.basicConsume(queueName, true, this.getConsumer());
	}

	@Override
	public void process(String message) throws ClassNotFoundException, ObjectStreamException
	{
		if (this.exchangeName == "CUSTOMER_ORDER_QUEUE")
		{
			CustomerOrder co = (CustomerOrder) xstream.fromXML(message);
		}
		else if (this.exchangeName == "MACHINE_ORDER_QUEUE")
		{
			MachineOrder mo = (MachineOrder) xstream.fromXML(message);
		}
		
	}

	@Override
	public void receive() throws ClassNotFoundException, ObjectStreamException
	{
		while (true)
		{
			QueueingConsumer.Delivery delivery;
			try
			{
				delivery = this.getConsumer().nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(" [x] ERP-Consumer(" + exchangeName + "): Received '" + message + "'");
				process(message);
			}
			catch (ShutdownSignalException e)
			{
				e.printStackTrace();
			}
			catch (ConsumerCancelledException e)
			{
				e.printStackTrace();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}

}
