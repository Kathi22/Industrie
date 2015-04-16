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

import dataSupplier.OPCType;
import dataSupplier.Types;

public class OPCConsumer extends Consumer
{
	EPServiceProvider epService;

	public OPCConsumer(String exchangeName) throws IOException
	{
		super(exchangeName);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(exchangeName, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, exchangeName, "");

		System.out.println("[*] OPC-Consumer(" + exchangeName + "): Waiting for messages.");

		this.setConsumer(new QueueingConsumer(channel));
		channel.basicConsume(queueName, true, this.getConsumer());
		epService = EPServiceProviderManager.getDefaultProvider();
		String expression = "select value from dataSupplier.OPCType.win:time(30 sec)";
		EPStatement statement = epService.getEPAdministrator().createEPL(
				expression);

		Listener listener = new Listener();
		statement.addListener(listener);
	}

	@Override
	public void process(String message) throws JAXBException
	{
		if (this.EXCHANGE_NAME == "MACHINE_DATA_QUEUE")
		{
			JAXBContext typesContext = JAXBContext.newInstance(OPCType.class);
			Unmarshaller um = typesContext.createUnmarshaller();
			OPCType t = (OPCType) um.unmarshal(new StreamSource(new StringReader(
				message)));
			epService.getEPRuntime().sendEvent(t);
		}
		else if (this.EXCHANGE_NAME == "NFC_DATA_QUEUE")
		{
			//TODO Verarbeitung NFC-Tag
		}

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
				System.out.println(" [x] OPC-Consumer(" + this.EXCHANGE_NAME + ") received '" + message + "'");
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
