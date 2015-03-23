package dataConsumer;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import dataSupplier.Types;

public class EventConsumer extends Consumer
{
	EPServiceProvider epService;

	public EventConsumer(String exchangeName) throws IOException
	{
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
		epService = EPServiceProviderManager.getDefaultProvider();
		String expression = "select quality from dataSupplier.Types.win:time(30 sec)";
		EPStatement statement = epService.getEPAdministrator().createEPL(
				expression);

		Listener listener = new Listener();
		statement.addListener(listener);
	}

	@Override
	public void process(String message) throws JAXBException
	{
		JAXBContext typesContext = JAXBContext.newInstance(Types.class);
		Unmarshaller um = typesContext.createUnmarshaller();
		Types t = (Types) um.unmarshal(new StreamSource(new StringReader(
				message)));
		epService.getEPRuntime().sendEvent(t);
	}

	@Override
	public void receive() throws JAXBException
	{
		while (true)
		{
			QueueingConsumer.Delivery delivery;
			try
			{
				delivery = this.getConsumer().nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(" [x] Received '" + message + "'");
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
