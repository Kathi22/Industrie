package dataConsumer;

import javax.xml.bind.JAXBException;

import com.rabbitmq.client.QueueingConsumer;

public abstract class Consumer {
	protected final String EXCHANGE_NAME;
	private QueueingConsumer consumer;
	//private Configuration config;
	
	public Consumer(String exchangeName) {
		this.EXCHANGE_NAME = exchangeName;
	}

	
	public abstract void receive() throws Exception;
	public abstract void process(String message) throws Exception;


	public QueueingConsumer getConsumer() {
		return consumer;
	}


	public void setConsumer(QueueingConsumer consumer) {
		this.consumer = consumer;
	}
	
	
}
